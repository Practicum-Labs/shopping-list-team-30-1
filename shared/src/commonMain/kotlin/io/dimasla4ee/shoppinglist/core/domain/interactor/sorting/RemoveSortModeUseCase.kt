package io.dimasla4ee.shoppinglist.core.domain.interactor.sorting

import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository

class RemoveSortModeUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(listId: Long) {
        settingsRepository.removeSortMode(listId)
    }
}