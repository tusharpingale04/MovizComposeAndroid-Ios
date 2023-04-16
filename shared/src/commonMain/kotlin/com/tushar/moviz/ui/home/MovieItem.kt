package com.tushar.moviz.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.tushar.moviz.bean.Movie
import com.tushar.moviz.utils.AppUtils
import com.tushar.moviz.utils.AsyncImage

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    val imageUrl = AppUtils.getImageUrl(movie.backdropPath)
    if (imageUrl != null) {
        Card(
            modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(16.dp)),
            elevation = 4.dp
        ) {
            AsyncImage(
                imageUrl = imageUrl,
                contentDescription = "movie poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                loadingPlaceHolder = {},
                errorPlaceHolder = {},
                alignment = Alignment.Center,
                alpha = 1.0f,
                coloFilter = null,
                filterQuality = FilterQuality.Medium
            )
        }
    }
}