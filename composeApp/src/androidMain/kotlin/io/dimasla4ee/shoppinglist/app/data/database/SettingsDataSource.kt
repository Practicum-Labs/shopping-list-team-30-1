package io.dimasla4ee.shoppinglist.app.data.database

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsDataSource(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme_mode")
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
}
