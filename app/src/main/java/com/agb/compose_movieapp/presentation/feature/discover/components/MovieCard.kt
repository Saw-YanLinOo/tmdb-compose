package com.agb.compose_movieapp.presentation.feature.discover.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agb.compose_movieapp.domain.model.MovieVO
import com.agb.compose_movieapp.presentation.utils.networkImagePainter

@Composable
fun MovieCard(modifier: Modifier = Modifier, movie: MovieVO, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .width(110.dp)
            .clickable {
                onClick()
            }
    ) {
        // Movie Poster
        Image(
            painter = networkImagePainter(movie.posterRes),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
        )

        // Movie Title
        Text(
            text = movie.title ?: "",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 6.dp)
        )

        // Movie Rating
        Text(
            text = movie.rating.toString(),
            color = Color(0xFFFFCC00),
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
        // Rating Stars
        RatingStars(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .padding(top = 2.dp),
            rating = movie.rating ?: 0.0,
        )
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    val movie = MovieVO(
        id = 1,
        title = "Avatar: The Way of Water",
        posterRes = "http://image.tmdb.org/t/p/w400/O7REXWPANWXvX2jhQydHjAq2DV.jpg",
        rating = 5.0
    )

    MovieCard(movie = movie, onClick = {})
}