package io.dimasla4ee.shoppinglist.app.data.repository

import io.dimasla4ee.shoppinglist.app.data.database.SettingsDataSource
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val dataSource: SettingsDataSource
) : SettingsRepository {

    override val themeMode: Flow<ThemeMode>
        get() = dataSource.observeThemeMode()

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataSource.setThemeMode(mode)
    }
}