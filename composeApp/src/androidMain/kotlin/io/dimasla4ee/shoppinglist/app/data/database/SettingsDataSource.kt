package io.dimasla4ee.shoppinglist.app.data.database

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsDataSource(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme_mode")
        private fun sortModeKey(listId: Long) = stringPreferencesKey("sort_mode_$listId")
    }

    fun observeThemeMode(): Flow<ThemeMode> = dataStore.data
        .map { preferences ->
            val value = preferences[THEME_KEY] ?: ThemeMode.SYSTEM.name
            ThemeMode.valueOf(value)
        }

    suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                this[THEME_KEY] = mode.name
            }
        }
    }

    suspend fun readThemeMode(): ThemeMode {
        val preferences = dataStore.data.first()
        val value = preferences[THEME_KEY] ?: ThemeMode.SYSTEM.name
        return ThemeMode.valueOf(value)
    }

    fun observeSortMode(listId: Long): Flow<SortMode> = dataStore.data
        .map { preferences ->
            val key = sortModeKey(listId)
            val value = preferences[key] ?: SortMode.CUSTOM.name
            SortMode.valueOf(value)
        }

    suspend fun setSortMode(listId: Long, mode: SortMode) {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                val key = sortModeKey(listId)
                this[key] = mode.name
            }
        }
    }

    suspend fun removeSortMode(listId: Long) {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                remove(sortModeKey(listId))
            }
        }
    }
}
