package com.agb.compose_movieapp.presentation.feature.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agb.compose_movieapp.presentation.navigation.Screens

fun NavGraphBuilder.loginScreen(
    modifier: Modifier,
    navController: NavController,
) {
    composable<Screens.LoginScreen> {
        LoginScreen(
            modifier = modifier,
            onTapLoginButton = {
                navController.navigate(Screens.DiscoverScreen) {
                    popUpTo(0) { inclusive = true } // Clear the entire back stack
                }
            }
        )
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier, onTapLoginButton: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(32.dp),
        ) {
            Text("Login", fontSize = 16.sp)
        }
    }
}