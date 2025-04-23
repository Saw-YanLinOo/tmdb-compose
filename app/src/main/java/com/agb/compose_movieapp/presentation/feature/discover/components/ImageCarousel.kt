package com.agb.compose_movieapp.presentation.feature.discover.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agb.compose_movieapp.domain.model.MovieVO
import com.agb.compose_movieapp.presentation.utils.networkImagePainter


@Composable
fun ImageCarousel(
    modifier: Modifier = Modifier,
    imageList: List<MovieVO>,
    pagerState: PagerState = rememberPagerState(initialPage = 0) { imageList.size },
    onClick: (MovieVO) -> Unit = {}
) {

    val isDragged = pagerState.interactionSource.collectIsDraggedAsState().value

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
                .height(250.dp)
                .fillMaxWidth()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick(imageList[page]) }
            ) {
                Image(
                    painter = networkImagePainter(imageList[page].posterRes),
                    contentDescription = "poster ${imageList[page].title}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background
                                ),
                                startY = 200f,
                                endY = 500f
                            )
                        )
                )
                Text(
                    text = imageList[page].title ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                )
                Box(
                    modifier = Modifier

                        .align(Alignment.Center)
                        .padding(bottom = 20.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(12.dp)
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "Play Icon",
                        modifier = Modifier
                            .size(38.dp)
                    )
                }

            }
        }

        DotsIndicator(
            modifier = Modifier
                .height(28.dp)
                .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
            totalDots = imageList.size,
            selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
            dotSize = 12.dp
        )
    }
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary /* Color.Yellow */,
    unSelectedColor: Color = MaterialTheme.colorScheme.secondary /* Color.Gray */,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->

            Box(
                modifier = modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(if (index == selectedIndex) selectedColor else unSelectedColor)
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Preview
@Composable
fun ImageCarouselPreview() {
    val movie = MovieVO(
        id = 1,
        title = "Avatar: The Way of Water",
        posterRes = "http://image.tmdb.org/t/p/w400/O7REXWPANWXvX2jhQydHjAq2DV.jpg",
        rating = 5.0,
    )

    ImageCarousel(
        imageList = listOf(movie, movie, movie, movie, movie),
        pagerState = rememberPagerState(initialPage = 0) { 5 },
        onClick = {}
    )
}