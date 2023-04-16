package com.tushar.moviz

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.push
import com.tushar.moviz.router.AppNavigator
import com.tushar.moviz.router.RoutedContent
import com.tushar.moviz.router.Router
import com.tushar.moviz.router.rememberRouter
import com.tushar.moviz.ui.details.DetailsScreen
import com.tushar.moviz.ui.home.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        val router: Router<AppNavigator> = rememberRouter(AppNavigator::class, listOf(AppNavigator.MoviesList))
        RoutedContent(
            router = router,
            animation = stackAnimation(slide())
        ) { screen ->
            when (screen) {
                is AppNavigator.MovieDetail -> {
                    DetailsScreen(
                        movie = screen.detail,
                        router = router
                    )
                }
                AppNavigator.MoviesList -> {
                    HomeScreen(
                        onMovieSelected = {
                            router.push(AppNavigator.MovieDetail(it))
                        },
                        router = router
                    )
                }
            }
        }
    }
}

expect fun getPlatformName(): String