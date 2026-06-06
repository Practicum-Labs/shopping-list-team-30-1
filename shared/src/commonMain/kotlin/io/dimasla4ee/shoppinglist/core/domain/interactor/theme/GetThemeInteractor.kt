package io.dimasla4ee.shoppinglist.core.domain.interactor.theme

import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetThemeInteractor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<ThemeMode> {
        return repository.themeMode
    }
}