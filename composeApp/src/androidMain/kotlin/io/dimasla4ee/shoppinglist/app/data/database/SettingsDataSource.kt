package io.dimasla4ee.shoppinglist.app.data.database

import android.content.SharedPreferences
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SettingsDataSource(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private const val THEME_KEY = "is_dark_theme"
    }

    fun observeDarkTheme(): Flow<Boolean> = callbackFlow {

        // отправляем текущее значение
        trySend(
            sharedPreferences.getBoolean(THEME_KEY, false)
        )

        val listener =
            SharedPreferences.OnSharedPreferenceChangeListener { _, key ->

                if (key == THEME_KEY) {
                    trySend(
                        sharedPreferences.getBoolean(THEME_KEY, false)
                    )
                }
            }

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    fun setDarkTheme(enabled: Boolean) {
        sharedPreferences.edit()
            .putBoolean(THEME_KEY, enabled)
            .apply()
    }
}