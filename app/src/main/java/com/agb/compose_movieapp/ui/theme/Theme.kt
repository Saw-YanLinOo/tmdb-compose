package com.agb.compose_movieapp.ui.theme

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

private val DarkColorScheme = darkColorScheme(
    primary = colorPrimary,
    secondary = colorSecondary,
    tertiary = colorTertiary,
    background = colorBackground,
)

private val LightColorScheme = lightColorScheme(
    primary = colorPrimary,
    secondary = colorSecondary,
    tertiary = colorTertiary,
    background = colorBackground,
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

@Composable
fun ComposemovieappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && supportsDynamicTheming() -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
//
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
//annotation class ThemePreviews
//
//@Preview(name = "English", locale = "en")
//@Preview(name = "Myanmar", locale = "my")
//@Preview(name = "Chinese", locale = "zh")
//annotation class LocalePreviews


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme - Myanmar", locale = "my"
)
annotation class MyanmarLightThemePreview

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme - English", locale = "en"
)
annotation class EnglishLightThemePreview

@Composable
fun AppPreviewWrapper(
    content: @Composable BoxWithConstraintsScope.() -> Unit,
) {
    ComposemovieappTheme {
        Surface {
            BoxWithConstraints {
                content()
            }
        }
    }
}

val LocalEntryPadding = compositionLocalOf { PaddingValues() }
