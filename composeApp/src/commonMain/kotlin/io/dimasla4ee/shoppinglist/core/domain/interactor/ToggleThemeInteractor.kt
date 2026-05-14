package io.dimasla4ee.shoppinglist.core.domain.interactor

import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository

class ToggleThemeInteractor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(current: Boolean) {
        repository.setDarkTheme(!current)
    }
}