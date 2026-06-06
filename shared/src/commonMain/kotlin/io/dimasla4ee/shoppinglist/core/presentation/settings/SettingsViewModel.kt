package io.dimasla4ee.shoppinglist.core.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.domain.interactor.theme.GetThemeInteractor
import io.dimasla4ee.shoppinglist.core.domain.interactor.theme.ToggleThemeInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    getThemeUseCase: GetThemeInteractor,
    private val toggleThemeUseCase: ToggleThemeInteractor
) : ViewModel() {

    val themeMode = getThemeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemeMode.SYSTEM
        )

    fun toggleTheme() {

        viewModelScope.launch {
            toggleThemeUseCase(themeMode.value)
        }
    }
}