package com.agb.compose_movieapp.presentation.feature.movie

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.agb.compose_movieapp.R
import com.agb.compose_movieapp.domain.model.MovieVO
import com.agb.compose_movieapp.presentation.feature.discover.components.RatingStars
import com.agb.compose_movieapp.presentation.navigation.Screens
import com.agb.compose_movieapp.ui.theme.AppPreviewWrapper

fun NavGraphBuilder.movieDetailScreen(
    navController: NavController
) {
    composable<Screens.MovieDetailScreen> { backStackEntry ->
        val product: Screens.MovieDetailScreen = backStackEntry.toRoute()
        MovieDetailScreen(id = product.id, navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(modifier: Modifier = Modifier, id: String, navController: NavController) {

    val movie = MovieVO(
        id = 1,
        title = "The Wolverine (2013), Official Trailer",
        posterRes = R.drawable.cinema_image,
        rating = 8f
    )

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {

            headerSection(movie = movie, popBack = {
                navController.popBackStack()
            })
            
            storyLine(movie = movie)

            items(20) { index ->
                Text(
                    text = "Movie detail item $index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

private fun LazyListScope.storyLine(movie: MovieVO) {
    item {
        Text(
            text = "STORYLINE",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
        Text(
            text = "After Po is tapped to become the Spiritual Leader of the Valley of Peace, he needs to find and train a new Dragon Warrior, while a wicked sorceress plans to re-summon all the master villains whom Po has vanquished to the spirit realm.",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}

private fun LazyListScope.headerSection(movie: MovieVO, popBack: () -> Unit = {}) {
    item {
        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = movie.posterRes),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background,
                            ),
                            startY = 500f,
                            endY = 800f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Text(
                        "2016",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(18.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Column(modifier = Modifier.padding(end = 8.dp)) {
                        RatingStars(
                            rating = movie.rating
                        )

                        Text(
                            "23,440 VOTES",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                        )
                    }
                    Text(
                        "4,57",
                        style = MaterialTheme.typography.displaySmall,

                        )
                }
                Text(
                    movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,

                    )
            }

            Row(modifier = Modifier.padding(20.dp)) {
                IconButton(onClick = popBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Light theme - Myanmar", locale = "my")
@Composable
internal fun MovieDetailScreenPreview() {
    AppPreviewWrapper {
        MovieDetailScreen(id = "1", navController = rememberNavController())
    }
}