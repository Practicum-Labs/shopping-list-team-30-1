package io.dimasla4ee.shoppinglist.core.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.dimasla4ee.shoppinglist.core.domain.interactor.GetThemeInteractor
import io.dimasla4ee.shoppinglist.core.domain.interactor.ToggleThemeInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    getThemeUseCase: GetThemeInteractor,
    private val toggleThemeUseCase: ToggleThemeInteractor
) : ViewModel() {

    val isDarkTheme = getThemeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun toggleTheme(current: Boolean) {

        viewModelScope.launch {
            toggleThemeUseCase(current)
        }
    }
}