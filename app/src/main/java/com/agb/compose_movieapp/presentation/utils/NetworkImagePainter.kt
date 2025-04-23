package com.agb.compose_movieapp.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.agb.compose_movieapp.R

@Composable
fun networkImagePainter(
    url: String?,
): Painter = rememberAsyncImagePainter(
    model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .crossfade(true)
        .error(R.drawable.baseline_error)
        .build(),
)