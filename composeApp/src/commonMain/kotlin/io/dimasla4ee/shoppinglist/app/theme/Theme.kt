package io.dimasla4ee.shoppinglist.app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColorScheme = lightColorScheme(

    primary = Beige500,
    onPrimary = Brown500,

    secondary = Brown300,
    onSecondary = Brown700,

    background = Beige100,
    onBackground = Brown700,

    surface = Gray300,
    onSurface = Brown700,

    surfaceVariant = Beige300,
    onSurfaceVariant = Brown400,

    outline = Brown400,
    outlineVariant = Gray500,

    error = Brown300,
    onError = White
)

private val DarkColorScheme = darkColorScheme(

    primary = Brown200,
    onPrimary = Black,

    secondary = Brown100,
    onSecondary = White,

    background = Brown800,
    onBackground = White,

    surface = Brown700,
    onSurface = White,

    surfaceVariant = Brown800,
    onSurfaceVariant = Gray300,

    outline = Gray300,
    outlineVariant = Gray400,

    error = Orange500,
    onError = Brown900
)

@Composable
fun ShoppingListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val appColors = if (darkTheme) DarkAppColors else LightAppColors

    CompositionLocalProvider(
        LocalAppColors provides appColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            //typography = AppTypography,
            content = content
        )
    }
}