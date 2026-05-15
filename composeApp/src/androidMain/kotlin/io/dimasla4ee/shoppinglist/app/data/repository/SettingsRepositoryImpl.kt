package io.dimasla4ee.shoppinglist.app.data.repository

import io.dimasla4ee.shoppinglist.app.data.database.SettingsDataSource
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val dataSource: SettingsDataSource
) : SettingsRepository {

    override val isDarkTheme: Flow<Boolean>
        get() = dataSource.observeDarkTheme()

    override suspend fun setDarkTheme(enabled: Boolean) {
        dataSource.setDarkTheme(enabled)
    }
}