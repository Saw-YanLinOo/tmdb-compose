package com.agb.compose_movieapp.presentation.feature.discover

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agb.compose_movieapp.R
import com.agb.compose_movieapp.domain.model.MovieVO
import com.agb.compose_movieapp.presentation.feature.discover.components.ImageCarousel
import com.agb.compose_movieapp.presentation.feature.discover.components.MovieCard
import com.agb.compose_movieapp.presentation.navigation.Screens
import com.agb.compose_movieapp.ui.theme.AppPreviewWrapper
import com.agb.compose_movieapp.ui.theme.LocalEntryPadding


fun NavGraphBuilder.discoverScreen(
    modifier: Modifier,
    navController: NavController,
) {
    composable<Screens.DiscoverScreen> {
        DiscoverScreen(
            modifier = modifier,
            navController = navController
        )
    }
}

@Composable
fun DiscoverScreen(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        val top = LocalEntryPadding.current.calculateTopPadding()
        val bottom = LocalEntryPadding.current.calculateBottomPadding()

//        val listState = rememberLazyListState(
//            // Scroll to item index 2, for example
//            initialFirstVisibleItemIndex = 10
//        )
        val movieList = listOf(
            MovieVO(
                id = 1,
                title = "The Wolverine (2013), Official Trailer",
                posterRes = R.drawable.cinema_image,
                rating = 6.5f
            ),
            MovieVO(
                id = 2,
                title = "Avatar: The Way of Water",
                posterRes = R.drawable.banner_image,
                rating = 5f
            ),
            MovieVO(
                id = 3,
                title = "Avatar (2009), Official Trailer",
                posterRes = R.drawable.cinema_image,
                rating = 7f
            ),
            MovieVO(
                id = 4,
                title = "Fast & Furious 7",
                posterRes = R.drawable.banner_image,
                rating = 8f
            ),
        )
        val tabs = listOf(
            "Action",
            "Adventure",
            "Animation",
            "Comedy",
            "Crime",
            "Documentary",
            "Drama",
            "Family",
            "Fantasy",
            "History",
            "Horror",
            "Music",
            "Mystery",
            "Romance"
        )

        LazyColumn(
//            state = listState,
            contentPadding = PaddingValues(top = 20.dp, bottom = bottom),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(top = top, bottom = 80.dp)
        ) {
            headerSection()
            bannerSection(
                movieList = movieList,
                onClick = { movie ->
                    navController.navigate(Screens.MovieDetailScreen(movie.id.toString()))
                }
            )
            titleAndHorizontalMovieListView(movieList = movieList)
            movieShowTimeSection()
            genresSection(
                tabs = tabs,
                movieList = movieList
            )
        }
    }
}

private fun LazyListScope.headerSection() {
    item {
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Millions of movies, TV shows and people to discover. Explore now.",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 6.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    stringResource(R.string.setting),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_search),
                    contentDescription = "Search Icon",
                    modifier = Modifier.padding(end = 16.dp),
                )
            }
        }
    }
}

private fun LazyListScope.bannerSection(
    movieList: List<MovieVO> = emptyList(),
    onClick: (MovieVO) -> Unit = {}
) {
    item {
        ImageCarousel(
            imageList = movieList,
            onClick = onClick
        )
    }
}

private fun LazyListScope.titleAndHorizontalMovieListView(movieList: List<MovieVO> = emptyList()) {
    item {
        Text(
            text = "Best Popular Movies And Series",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
        ) {
            movieList.forEach { movie ->
                item {
                    MovieCard(movie = movie, onClick = {})
                }
            }
        }

    }
}

private fun LazyListScope.movieShowTimeSection() {
    item {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(20.dp)
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.06f))
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    "Check Movie \nShowtimes",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(20.dp)
                )
                Text(
                    "See More",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFFFFDE00),
                    modifier = Modifier.padding(20.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .padding(32.dp)
                    .size(56.dp)
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)

            )
        }
    }
}

private fun LazyListScope.genresSection(
    tabs: List<String>,
    movieList: List<MovieVO> = emptyList()
) {
    item {
        Column {
            var selectedTabIndex by remember { mutableIntStateOf(0) }

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 16.dp,
                contentColor = Color.Gray,
                containerColor = MaterialTheme.colorScheme.background,
                divider = { HorizontalDivider(color = Color.Transparent) },
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        color = Color.Yellow,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .fillMaxWidth()
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        modifier = Modifier.padding(8.dp),
                        content = {
                            Text(
                                text = tab,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(8.dp),

                                )
                        }
                    )
                }
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier
                    .padding(top = 8.dp)

            ) {
                movieList.forEach { movie ->
                    item {
                        MovieCard(movie = movie, onClick = {})
                    }
                }
            }
        }
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Light theme - Myanmar", locale = "my")
@Composable
internal fun DiscoverContentPreview() {
    AppPreviewWrapper {
        DiscoverScreen(
            modifier = Modifier,
            navController = rememberNavController()
        )
    }
}