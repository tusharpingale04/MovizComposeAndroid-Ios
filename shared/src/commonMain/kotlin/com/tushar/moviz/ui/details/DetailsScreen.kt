package com.tushar.moviz.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.router.stack.pop
import com.tushar.moviz.bean.Movie
import com.tushar.moviz.router.AppNavigator
import com.tushar.moviz.router.Router
import com.tushar.moviz.utils.statusBarPadding

@Composable
fun DetailsScreen(
    movie: Movie,
    router: Router<AppNavigator>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movie Details",
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth(),
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { router.pop() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = Color.White,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            )
        },
        content = {
            Box(Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center).padding(horizontal = 16.dp),
                    text = movie.overview ?: "NA",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        },
        modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.statusBarPadding)
    )
}