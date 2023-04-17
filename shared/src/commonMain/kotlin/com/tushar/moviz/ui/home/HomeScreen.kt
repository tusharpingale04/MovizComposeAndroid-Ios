package com.tushar.moviz.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tushar.moviz.bean.Movie
import com.tushar.moviz.router.AppNavigator
import com.tushar.moviz.router.Router
import com.tushar.moviz.router.rememberViewModel
import com.tushar.moviz.state.HomeState
import com.tushar.moviz.utils.statusBarPadding

@Composable
fun HomeScreen(
    router: Router<AppNavigator>,
    onMovieSelected: (Movie) -> Unit = {}
) {
    val viewModel: HomeViewModel =
        rememberViewModel(HomeViewModel::class) { savedState -> HomeViewModel(savedState) }

    val state = viewModel.state.collectAsState()

    HomeContent(
        state = state.value,
        onMovieSelected = onMovieSelected,
        onRetryClick = {
            viewModel.fetchMovies()
        }
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    onMovieSelected: (Movie) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    if (state.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center).size(24.dp)
            )
        }
    } else if (state.isError) {
        ErrorScreen(onRetryClick = onRetryClick)
    } else {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Trending Movies",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    backgroundColor = Color.White,
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                )
            },
            content = {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(state.movies.size) { index ->
                        val item = state.movies[index]
                        MovieItem(Modifier.clickable {
                            onMovieSelected(item)
                        }, item)
                    }
                }
            },
            modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.statusBarPadding)
        )
    }

}

@Composable
fun ErrorScreen(onRetryClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Something went wrong. Please try again!",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium
        )
        Button(onClick = {
            onRetryClick()
        }, modifier = Modifier.padding(top = 16.dp).wrapContentSize()) {
            Text(
                text = "Retry",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.wrapContentSize(),
                fontWeight = FontWeight.Medium
            )
        }
    }
}
