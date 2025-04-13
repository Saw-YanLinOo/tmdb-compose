package com.agb.compose_movieapp.presentation.feature.changelanguage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agb.compose_movieapp.R
import com.agb.compose_movieapp.presentation.navigation.Screens
import com.agb.compose_movieapp.ui.theme.AppPreviewWrapper
import com.agb.compose_movieapp.ui.theme.ThemePreviews

fun NavGraphBuilder.changeLanguageScreen(
    modifier: Modifier,
    navController: NavController,
) {
    composable<Screens.ChangeLanguageScreen> {
        ChangeLanguageScreen(
            modifier = modifier,
            popUp = {
                navController.popBackStack()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeLanguageScreen(modifier: Modifier = Modifier, popUp: () -> Unit) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Change Language") },
                navigationIcon = {
                    IconButton(onClick = popUp) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // English option
            LanguageOption(
                flagResource = R.drawable.ic_launcher_background,
                languageName = "English",
                isSelected = true
            )

            // Myanmar option
            LanguageOption(
                flagResource = R.drawable.ic_launcher_background,
                languageName = "မြန်မာဘာသာ",
                isSelected = false
            )
        }
    }
}

@Composable
fun LanguageOption(
    flagResource: Int,
    languageName: String,
    isSelected: Boolean
) {
    Card(
        onClick = { /* Handle language selection */ },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFE8EFFF) else Color.White
        ),
        border = if (isSelected) BorderStroke(1.dp, Color(0xFF6B89D3)) else null,
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 0.dp else 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = flagResource),
                contentDescription = "$languageName flag",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = languageName,
                fontSize = 16.sp,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                color = if (isSelected) Color(0xFF3B68C9) else Color.Black
            )
        }
    }
}

@ThemePreviews
@Composable
internal fun ChangeLanguageScreenPreview() {
    AppPreviewWrapper {
        ChangeLanguageScreen(
            modifier = Modifier,
            popUp = {}
        )
    }
}