package com.agb.compose_movieapp.presentation.navigation

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.agb.compose_movieapp.R

@Composable
fun ButtonNavigation(modifier: Modifier = Modifier, navController: NavController) {

    val items = listOf(BottomNavItem.Discover, BottomNavItem.Favorite, BottomNavItem.Setting)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedBottomNavItemIndex by remember(key1 = currentRoute) {
        derivedStateOf {
            when (currentRoute) {
                Screens.DiscoverScreen::class.qualifiedName -> 0
                Screens.FavoriteScreen::class.qualifiedName -> 1
                Screens.SettingScreen::class.qualifiedName -> 2
                else -> -1
            }
        }
    }

    if (selectedBottomNavItemIndex == -1) return

    Log.d("CurrentRoute", "ButtonNavigation: $currentRoute")

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                spotColor = MaterialTheme.colorScheme.background,
            )
            .height(86.dp),
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedBottomNavItemIndex == index,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.screen) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(0) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(
                        text = stringResource(item.label),
                        color = MaterialTheme.colorScheme.primary,
                    )
                },
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = "${item.label}",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .width(46.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                if (selectedBottomNavItemIndex == index) MaterialTheme.colorScheme.secondary.copy(
                                    alpha = 0.4f
                                ) else Color.Transparent
                            )
                            .padding(vertical = 2.dp)

                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,

                    )
            )
        }
    }
}

sealed class BottomNavItem(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val screen: Screens
) {
    object Discover : BottomNavItem(
        R.string.discover,
        R.drawable.baseline_explore,
        Screens.DiscoverScreen
    )

    object Favorite :
        BottomNavItem(R.string.favorite, R.drawable.baseline_favorite, Screens.FavoriteScreen)

    object Setting :
        BottomNavItem(R.string.setting, R.drawable.baseline_settings, Screens.SettingScreen)
}