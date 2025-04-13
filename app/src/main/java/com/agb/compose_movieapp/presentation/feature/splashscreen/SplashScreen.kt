package com.agb.compose_movieapp.presentation.feature.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.agb.compose_movieapp.R
import com.agb.compose_movieapp.presentation.navigation.Screens
import kotlinx.coroutines.delay

fun NavGraphBuilder.splashScreen(
    modifier: Modifier,
    navController: NavController,
) {
    composable<Screens.SplashScreen> {
        SplashScreen(
            modifier = modifier,
            onNavigateToHome = {
                navController.navigate(Screens.DiscoverScreen) {
                    popUpTo(Screens.SplashScreen) {
                        inclusive = true
                    }
                }
            }
        )
    }
}


@Composable
fun SplashScreen(modifier: Modifier = Modifier, onNavigateToHome: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(5000L)
        onNavigateToHome()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.img_onboard), // replace with your image
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
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
                            Color.Black.copy(alpha = 0.7f),
                        ),
                        startY = 500f,
                        endY = 1000f
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)

                .padding(horizontal = 24.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = "UPCOMMING NEXT MONTH",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Tudo sobre filmes, séries, animes e muito mais.",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Fique por dentro das informações de filmes, séries, animes e muito mais.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White.copy(alpha = 0.9f),
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onNavigateToHome() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(32.dp),
            ) {
                Text("Acessar", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        modifier = Modifier.fillMaxSize(),
        onNavigateToHome = {}
    )
}