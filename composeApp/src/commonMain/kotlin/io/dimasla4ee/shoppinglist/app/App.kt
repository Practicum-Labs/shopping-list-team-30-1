package io.dimasla4ee.shoppinglist.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.navigation.NavigationRoot
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.settings.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@PreviewLightDark
fun App() {

    val viewModel: SettingsViewModel = koinViewModel()

    val isDarkTheme by viewModel
        .isDarkTheme
        .collectAsState()

    ShoppingListTheme(
        darkTheme = isDarkTheme
    ) {

        NavigationRoot(
            modifier = Modifier.fillMaxSize(),

            onThemeToggle = {
                viewModel.toggleTheme(isDarkTheme)
            }
        )
    }
}