package io.dimasla4ee.shoppinglist.core.domain.repository

import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val themeMode: Flow<ThemeMode>

    suspend fun setThemeMode(mode: ThemeMode)

    fun observeSortMode(listId: Long): Flow<SortMode>
    suspend fun setSortMode(listId: Long, mode: SortMode)
    suspend fun removeSortMode(listId: Long)
}