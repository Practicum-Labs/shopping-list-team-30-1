package io.dimasla4ee.shoppinglist.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class TokenStorageImpl(
    private val dataStore: DataStore<Preferences>
) : TokenStorage {

    private companion object {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    private val preferencesFlow: Flow<Preferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
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

    override fun observeAccessToken(): Flow<String?> = preferencesFlow
        .map { it[ACCESS_TOKEN_KEY] }
        .distinctUntilChanged()

    override fun observeRefreshToken(): Flow<String?> = preferencesFlow
        .map { it[REFRESH_TOKEN_KEY] }
        .distinctUntilChanged()

    override suspend fun getAccessToken(): String? = observeAccessToken().first()
    override suspend fun getRefreshToken(): String? = observeRefreshToken().first()

    override suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }
}