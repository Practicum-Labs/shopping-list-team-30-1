package io.dimasla4ee.shoppinglist.core.domain.interactor

import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetThemeInteractor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.isDarkTheme
    }
}