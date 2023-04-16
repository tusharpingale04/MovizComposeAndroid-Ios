package com.tushar.moviz

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.tushar.moviz.router.LocalComponentContext

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = ComposeUIViewController {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

    CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        App()
    }
}