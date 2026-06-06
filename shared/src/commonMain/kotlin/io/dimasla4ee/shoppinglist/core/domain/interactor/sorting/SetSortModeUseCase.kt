package io.dimasla4ee.shoppinglist.core.domain.interactor.sorting

import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode

class SetSortModeUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(listId: Long, mode: SortMode) {
        settingsRepository.setSortMode(listId, mode)
    }
}