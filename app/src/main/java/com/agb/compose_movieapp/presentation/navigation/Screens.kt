package com.agb.compose_movieapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens() {
    @Serializable
    object SplashScreen : Screens()

    @Serializable
    object LoginScreen : Screens()

    @Serializable
    object DiscoverScreen : Screens()

    @Serializable
    object FavoriteScreen : Screens()

    @Serializable
    object SettingScreen : Screens()

    @Serializable
    object ChangeLanguageScreen : Screens()

    @Serializable
    data class MovieDetailScreen(val id: String) : Screens()
}