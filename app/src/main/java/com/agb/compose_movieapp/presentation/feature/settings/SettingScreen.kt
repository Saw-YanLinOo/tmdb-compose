package com.agb.compose_movieapp.presentation.feature.settings

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agb.compose_movieapp.R
import com.agb.compose_movieapp.presentation.navigation.Screens
import com.agb.compose_movieapp.ui.theme.AppPreviewWrapper

fun NavGraphBuilder.settingScreen(
    modifier: Modifier,
    navController: NavController,
) {
    composable<Screens.SettingScreen> {
        SettingScreen(
            modifier = modifier,
            changeLanguage = {
                navController.navigate(Screens.ChangeLanguageScreen)
            }
        )
    }
}

@Composable
fun SettingScreen(modifier: Modifier = Modifier, changeLanguage: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
        ) {

            Text(
                stringResource(R.string.setting),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            // Settings options
            Column {
                SettingsItem(Icons.Default.Person, stringResource(R.string.setting), onClick = {})
                SettingsItem(
                    Icons.Default.Edit,
                    stringResource(R.string.change_language),
                    onClick = changeLanguage
                )
            }


            Button(
                onClick = { /* Handle logout */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE4572E)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Logout", color = Color.White)
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.6f))

            Text(
                text = "Version 2.0.0",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            )

        }
    }

}

@Composable
fun SettingsItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Card(

        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF3955BC), // Soft blue
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next",
                tint = Color.Gray
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme - Myanmar", locale = "my")
@Composable
internal fun DiscoverContentPreview() {
    AppPreviewWrapper {
        SettingScreen(
            modifier = Modifier,
            changeLanguage = {}
        )
    }
}