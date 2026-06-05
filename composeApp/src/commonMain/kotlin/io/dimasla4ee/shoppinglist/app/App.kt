package io.dimasla4ee.shoppinglist.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.navigation.NavigationRoot
import io.dimasla4ee.shoppinglist.app.startup.session.presentation.SessionViewModel
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.settings.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    modifier: Modifier = Modifier
) {
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val sessionViewModel: SessionViewModel = koinViewModel()
    val themeMode by settingsViewModel.themeMode.collectAsState()
    val systemDarkTheme = isSystemInDarkTheme()
    val isDarkTheme = when (themeMode) {
        ThemeMode.SYSTEM -> systemDarkTheme
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
    }

    LaunchedEffect(Unit) {
        sessionViewModel.observeSession()
    }

    ShoppingListTheme(
        darkTheme = isDarkTheme,
        themeMode = themeMode
    ) {
        NavigationRoot(
            sessionViewModel = sessionViewModel,
            onThemeToggle = settingsViewModel::toggleTheme,
            modifier = modifier.fillMaxSize()
        )
    }
}