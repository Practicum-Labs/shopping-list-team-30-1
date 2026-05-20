package io.dimasla4ee.shoppinglist.app.data.repository

import io.dimasla4ee.shoppinglist.app.data.database.SettingsDataSource
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val dataSource: SettingsDataSource
) : SettingsRepository {

    override val themeMode: Flow<ThemeMode>
        get() = dataSource.observeThemeMode()

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataSource.setThemeMode(mode)
    }

    override fun observeSortMode(listId: Long): Flow<SortMode> =
        dataSource.observeSortMode(listId)

    override suspend fun setSortMode(listId: Long, mode: SortMode) {
        dataSource.setSortMode(listId, mode)
    }

    override suspend fun removeSortMode(listId: Long) {
        dataSource.removeSortMode(listId)
    }
}

