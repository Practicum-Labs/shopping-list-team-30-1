package io.dimasla4ee.shoppinglist.core.domain.interactor.theme

import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository

class ToggleThemeInteractor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(current: ThemeMode) {
        val next = when (current) {
            ThemeMode.SYSTEM -> ThemeMode.DARK
            ThemeMode.DARK -> ThemeMode.LIGHT
            ThemeMode.LIGHT -> ThemeMode.SYSTEM
        }

        repository.setThemeMode(next)
    }
}