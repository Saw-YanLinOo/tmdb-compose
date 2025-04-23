package com.agb.compose_movieapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.agb.compose_movieapp.presentation.feature.changelanguage.changeLanguageScreen
import com.agb.compose_movieapp.presentation.feature.discover.discoverScreen
import com.agb.compose_movieapp.presentation.feature.favorite.favoriteScreen
import com.agb.compose_movieapp.presentation.feature.login.loginScreen
import com.agb.compose_movieapp.presentation.feature.movie.movieDetailScreen
import com.agb.compose_movieapp.presentation.feature.settings.settingScreen
import com.agb.compose_movieapp.presentation.feature.splashscreen.splashScreen
import com.agb.compose_movieapp.ui.theme.ComposemovieappTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetUpNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Screens = Screens.SplashScreen,
) {

    Scaffold(
        bottomBar = {
            ButtonNavigation(modifier = modifier, navController = navController)
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            splashScreen(modifier = modifier, navController = navController)

            loginScreen(modifier = modifier, navController = navController)

            discoverScreen(modifier = modifier, navController = navController)
            favoriteScreen(modifier = modifier, navController = navController)
            settingScreen(modifier = modifier, navController = navController)

            changeLanguageScreen(modifier = modifier, navController = navController)
            movieDetailScreen(navController = navController)

        }
    }
}

@Preview(showBackground = false)
@Composable
fun SetUpNavGraphPreview() {
    ComposemovieappTheme {
        SetUpNavGraph(
            modifier = Modifier.fillMaxSize(),
            navController = rememberNavController(),
            startDestination = Screens.DiscoverScreen
        )
    }
}