package com.agb.compose_movieapp.presentation.feature.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agb.compose_movieapp.presentation.navigation.Screens


fun NavGraphBuilder.favoriteScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    composable<Screens.FavoriteScreen> {
        FavoriteScreen(modifier = modifier, navController = navController)
    }
}

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier, navController: NavController) {

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                "Favorite",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 32.sp,
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
        }
    }
}