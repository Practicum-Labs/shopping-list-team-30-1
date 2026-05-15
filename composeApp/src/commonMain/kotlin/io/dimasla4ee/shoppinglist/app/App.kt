package io.dimasla4ee.shoppinglist.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.navigation.NavigationRoot
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.settings.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@PreviewLightDark
fun App() {

    val viewModel: SettingsViewModel = koinViewModel()

    val themeMode by viewModel.themeMode.collectAsState()

    val systemDarkTheme = isSystemInDarkTheme()

    val isDarkTheme = when (themeMode) {
        ThemeMode.SYSTEM -> systemDarkTheme
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
    }

    ShoppingListTheme(
        darkTheme = isDarkTheme,
        themeMode = themeMode
    ) {

        NavigationRoot(
            onThemeToggle = viewModel::toggleTheme,
            isDarkTheme = isDarkTheme,
            modifier = Modifier.fillMaxSize()
        )
    }
}