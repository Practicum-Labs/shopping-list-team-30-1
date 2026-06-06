package io.dimasla4ee.shoppinglist.app.startup.session.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import io.dimasla4ee.shoppinglist.app.startup.session.domain.AppLaunchRepository
import kotlinx.coroutines.flow.first

class AppLaunchRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : AppLaunchRepository {

    private companion object {
        val FIRST_LAUNCH_KEY = booleanPreferencesKey("first_launch")
    }

    override suspend fun isFirstLaunch(): Boolean {
        return dataStore.data.first()[FIRST_LAUNCH_KEY] != false
    }

    override suspend fun setLaunched() {

        dataStore.edit { preferences ->
            preferences[FIRST_LAUNCH_KEY] = false
        }
    }
}