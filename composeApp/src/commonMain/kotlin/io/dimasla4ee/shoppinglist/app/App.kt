package io.dimasla4ee.shoppinglist.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.navigation.NavigationRoot
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme

@Composable
@PreviewLightDark
fun App() {
    ShoppingListTheme {
        NavigationRoot(modifier = Modifier.fillMaxSize())
    }
}