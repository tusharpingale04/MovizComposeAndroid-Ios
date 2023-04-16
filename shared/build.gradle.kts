import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
    id("com.codingfeline.buildkonfig")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            export("com.arkivanov.decompose:decompose:2.0.0-compose-experimental-alpha-02")
            export("com.arkivanov.decompose:extensions-compose-jetbrains:2.0.0-compose-experimental-alpha-02")
            export("com.arkivanov.essenty:parcelable:1.1.0")
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("io.ktor:ktor-client-core:2.2.4")
                implementation("io.ktor:ktor-client-content-negotiation:2.0.3")
                implementation("io.ktor:ktor-client-logging:2.0.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")
                implementation("app.cash.molecule:molecule-runtime:0.7.1")
                api("com.arkivanov.decompose:decompose:2.0.0-compose-experimental-alpha-02")
                api("com.arkivanov.decompose:extensions-compose-jetbrains:2.0.0-compose-experimental-alpha-02")
                api("com.arkivanov.essenty:parcelable:1.1.0")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.0")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.0")
                implementation("io.coil-kt:coil-compose:2.3.0")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.2.4")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.tushar.moviz.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

buildkonfig {
    packageName = "com.tushar.moviz"

    defaultConfigs {
        val apiKey: String = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(
            rootDir
        ).getProperty("apiKey")

        require(apiKey.isNotEmpty()) {
            "Register your api key from https://www.themoviedb.org/ and place it in local.properties as `apiKey`"
        }

        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "API_KEY", apiKey)
    }
}
