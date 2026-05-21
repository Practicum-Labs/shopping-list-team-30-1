package io.dimasla4ee.shoppinglist.core.domain.storage

interface TokenStorage {
    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String
    )

    suspend fun getAccessToken(): String?

    suspend fun getRefreshToken(): String?

    suspend fun clearTokens()
}