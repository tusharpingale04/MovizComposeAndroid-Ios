plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.kotlin.jvm").apply(false)
    id("org.jetbrains.compose").apply(false)
}

buildscript {
    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.4.0")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.20")
        classpath("app.cash.molecule:molecule-gradle-plugin:0.7.1")
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.13.3")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}
