package com.agb.compose_movieapp.presentation.feature.discover.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.agb.compose_movieapp.R


@Composable
fun RatingStars(modifier: Modifier = Modifier, rating: Float) {
    Row(
        modifier = Modifier
            .padding(bottom = 4.dp)
            .padding(top = 2.dp)
    ) {
        val fullStars = (rating / 2).toInt()
        val hasHalfStar = rating / 2 - fullStars >= 0.5

        repeat(5) { index ->
            val starRes = when {
                index < fullStars -> R.drawable.baseline_star
                index == fullStars && hasHalfStar -> R.drawable.baseline_star_half
                else -> R.drawable.baseline_star_outline
            }

            Image(
                painter = painterResource(id = starRes),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}