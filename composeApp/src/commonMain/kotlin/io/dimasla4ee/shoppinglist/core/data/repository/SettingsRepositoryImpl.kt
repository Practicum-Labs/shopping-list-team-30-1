package io.dimasla4ee.shoppinglist.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    private companion object {
        val THEME_KEY = stringPreferencesKey("theme_mode")
        fun sortModeKey(listId: Long) = stringPreferencesKey("sort_mode_$listId")
    }

    override val themeMode: Flow<ThemeMode> =
        dataStore.data.map { preferences ->
            val raw = preferences[THEME_KEY]
            runCatching { ThemeMode.valueOf(raw ?: ThemeMode.SYSTEM.name) }
                .getOrDefault(ThemeMode.SYSTEM)
        }

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = mode.name
        }
    }

    override fun observeSortMode(listId: Long): Flow<SortMode> =
        dataStore.data.map { preferences ->
            val raw = preferences[sortModeKey(listId)]
            runCatching { SortMode.valueOf(raw ?: SortMode.CUSTOM.name) }
                .getOrDefault(SortMode.CUSTOM)
        }

    override suspend fun setSortMode(listId: Long, mode: SortMode) {
        dataStore.edit { preferences ->
            preferences[sortModeKey(listId)] = mode.name
        }
    }

    override suspend fun removeSortMode(listId: Long) {
        dataStore.edit { preferences ->
            preferences.remove(sortModeKey(listId))
        }
    }
}