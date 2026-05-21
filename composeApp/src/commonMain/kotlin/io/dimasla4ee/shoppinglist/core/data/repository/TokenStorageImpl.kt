package io.dimasla4ee.shoppinglist.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage
import kotlinx.coroutines.flow.first

class TokenStorageImpl(
    private val dataStore: DataStore<Preferences>
) : TokenStorage {

    private companion object {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    override suspend fun saveTokens(
        accessToken: String,
        refreshToken: String
    ) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    override suspend fun getAccessToken(): String? {
        return dataStore.data.first()[ACCESS_TOKEN_KEY]
    }

    override suspend fun getRefreshToken(): String? {
        return dataStore.data.first()[REFRESH_TOKEN_KEY]
    }

    override suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }
}