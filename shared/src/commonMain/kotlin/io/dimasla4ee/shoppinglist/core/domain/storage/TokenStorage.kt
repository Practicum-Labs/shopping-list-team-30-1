package io.dimasla4ee.shoppinglist.core.domain.storage

import kotlinx.coroutines.flow.Flow

interface TokenStorage {
    fun observeAccessToken(): Flow<String?>
    fun observeRefreshToken(): Flow<String?>

    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?

    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String
    )

    suspend fun clearTokens()
}