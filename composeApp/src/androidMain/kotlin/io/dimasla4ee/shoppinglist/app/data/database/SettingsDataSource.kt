package io.dimasla4ee.shoppinglist.app.data.database

import android.content.SharedPreferences
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SettingsDataSource(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private const val THEME_KEY = "theme_mode"
    }

    fun observeThemeMode(): Flow<ThemeMode> = callbackFlow {
        trySend(
            readThemeMode()
        )
        val listener =
            SharedPreferences.OnSharedPreferenceChangeListener { _, key ->

                if (key == THEME_KEY) {
                    trySend(readThemeMode())
                }
            }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        sharedPreferences.edit()
            .putString(THEME_KEY, mode.name)
            .apply()
    }

    private fun readThemeMode(): ThemeMode {
        val value = sharedPreferences.getString(
            THEME_KEY,
            ThemeMode.SYSTEM.name
        )

        return ThemeMode.valueOf(
            value ?: ThemeMode.SYSTEM.name
        )
    }
}