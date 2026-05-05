package io.dimasla4ee.shoppinglist.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.welcome_screen.ui.WelcomeScreen

@Composable
@PreviewLightDark
fun App() {
    ShoppingListTheme {
        WelcomeScreen(
            onGoToShopping = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}