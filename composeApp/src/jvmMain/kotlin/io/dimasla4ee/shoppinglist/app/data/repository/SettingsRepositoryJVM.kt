package io.dimasla4ee.shoppinglist.app.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryJVM(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    private companion object {
        val THEME_KEY = stringPreferencesKey("theme_mode")
        private fun sortModeKey(listId: Long) = stringPreferencesKey("sort_mode_$listId")
    }

    override val themeMode: Flow<ThemeMode> = dataStore.data.map { preferences ->
        val modeString = preferences[THEME_KEY] ?: "SYSTEM"
        ThemeMode.valueOf(modeString)
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = mode.name
        }
    }

    override fun observeSortMode(listId: Long): Flow<SortMode> = dataStore.data
        .map { preferences ->
            val key = sortModeKey(listId)
            val value = preferences[key] ?: SortMode.CUSTOM.name
            SortMode.valueOf(value)
        }

    override suspend fun setSortMode(listId: Long, mode: SortMode) {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                val key = sortModeKey(listId)
                this[key] = mode.name
            }
        }
    }

    override suspend fun removeSortMode(listId: Long) {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                remove(sortModeKey(listId))
            }
        }
    }
}
