package io.dimasla4ee.shoppinglist.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme = lightColorScheme(

    primary = Beige500,
    onPrimary = Brown500,

    primaryContainer = Beige250,
    onPrimaryContainer = Brown300,

    secondary = Brown300,
    onSecondary = Brown700,

    tertiary = Beige300,
    onTertiary = Brown400,

    tertiaryContainer = Beige400,
    onTertiaryContainer = Brown600,

    background = Beige100,
    onBackground = Brown700,

    surface = Gray300,
    onSurface = Brown700,

    surfaceVariant = Beige300,
    onSurfaceVariant = Brown400,

    surfaceContainerLow = Beige100,

    outline = Brown400,
    outlineVariant = Gray500,

    error = Brown300,
    onError = White,

    surfaceContainerHigh = Beige150,
    surfaceContainerHighest = Brown600

)

private val DarkColorScheme = darkColorScheme(

    primary = Brown200,
    onPrimary = Black,

    primaryContainer = Brown550,
    onPrimaryContainer = Orange500,

    secondary = Brown100,
    onSecondary = White,

    tertiary = Brown550,
    onTertiary = Brown150,

    tertiaryContainer = Brown250,
    onTertiaryContainer = Black,

    background = Brown800,
    onBackground = Beige50,

    surface = Brown700,
    onSurface = White,

    surfaceVariant = Brown800,
    onSurfaceVariant = Gray300,

    surfaceContainerLow = Brown550,

    outline = Gray300,
    outlineVariant = Gray400,

    error = Orange500,
    onError = Brown900,

    surfaceContainerHigh = Brown700,
    surfaceContainerHighest = Brown600
)

val LocalIsDarkTheme = staticCompositionLocalOf { false }

val LocalThemeMode =
    staticCompositionLocalOf<ThemeMode> {
        ThemeMode.SYSTEM
    }

@Composable
fun ShoppingListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val appColors = if (darkTheme) DarkAppColors else LightAppColors
    val appPlaceholders = if (darkTheme) DarkAppPlaceholders else LightAppPlaceholders

    CompositionLocalProvider(
        LocalAppColors provides appColors,
        LocalAppPlaceholders provides appPlaceholders,
        LocalIsDarkTheme provides darkTheme,
        LocalThemeMode provides themeMode
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content
        )
    }
}