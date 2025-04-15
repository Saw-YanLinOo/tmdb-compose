package com.agb.compose_movieapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.agb.compose_movieapp.presentation.feature.changelanguage.changeLanguageScreen
import com.agb.compose_movieapp.presentation.feature.discover.discoverScreen
import com.agb.compose_movieapp.presentation.feature.favorite.favoriteScreen
import com.agb.compose_movieapp.presentation.feature.login.loginScreen
import com.agb.compose_movieapp.presentation.feature.movie.movieDetailScreen
import com.agb.compose_movieapp.presentation.feature.settings.settingScreen
import com.agb.compose_movieapp.presentation.feature.splashscreen.splashScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetUpNavGraph(modifier: Modifier = Modifier, navController: NavHostController) {

    Scaffold(
        bottomBar = {
            ButtonNavigation(modifier = modifier, navController = navController)
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = Screens.SplashScreen,
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