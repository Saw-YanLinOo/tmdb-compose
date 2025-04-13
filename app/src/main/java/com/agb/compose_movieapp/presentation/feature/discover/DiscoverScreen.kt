package com.agb.compose_movieapp.presentation.feature.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agb.compose_movieapp.R
import com.agb.compose_movieapp.presentation.navigation.Screens
import com.agb.compose_movieapp.ui.theme.AppPreviewWrapper
import com.agb.compose_movieapp.ui.theme.LocalEntryPadding
import com.agb.compose_movieapp.ui.theme.ThemePreviews


fun NavGraphBuilder.discoverScreen(
    modifier: Modifier,
    navController: NavController,
) {
    composable<Screens.DiscoverScreen> {
        DiscoverScreen(
            modifier = modifier,
        )
    }
}

@Composable
fun DiscoverScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {

        val top = LocalEntryPadding.current.calculateTopPadding()
        val bottom = LocalEntryPadding.current.calculateBottomPadding()

        LazyColumn(
            contentPadding = PaddingValues(top=20.dp, bottom = bottom),
            modifier = Modifier
                .padding(top=top)
        ) {
            headerSection()
        }
    }
}

private fun LazyListScope.headerSection() {
    item {
        Text(
            text = "Welcome,",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
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
                    "Search",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
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

private fun LazyListScope.searchBarSection(
) {

}

@ThemePreviews
@Composable
internal fun DiscoverContentPreview() {
    AppPreviewWrapper {
        DiscoverScreen()
    }
}