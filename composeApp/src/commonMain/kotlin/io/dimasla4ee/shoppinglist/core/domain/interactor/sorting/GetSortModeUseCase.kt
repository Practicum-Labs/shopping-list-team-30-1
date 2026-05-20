package io.dimasla4ee.shoppinglist.core.domain.interactor.sorting

import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import kotlinx.coroutines.flow.Flow

class GetSortModeUseCase(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(listId: Long): Flow<SortMode> =
        settingsRepository.observeSortMode(listId)
}