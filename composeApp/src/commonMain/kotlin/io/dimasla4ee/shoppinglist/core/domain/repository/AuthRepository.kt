package io.dimasla4ee.shoppinglist.core.domain.repository

import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response

interface AuthRepository {

    suspend fun register(
        email: String,
        password: String
    ): DomainResult<Response.RegisterResponse, NetworkError>

    suspend fun login(
        email: String,
        password: String
    ): DomainResult<Response.UserAuthResponse, NetworkError>

    suspend fun refresh(
        refreshToken: String
    ): DomainResult<Response.RefreshTokenResponse, NetworkError>

    suspend fun recoverPassword(): DomainResult<Response.RecoverPasswordResponse, NetworkError>

    suspend fun checkToken(
        accessToken: String
    ): DomainResult<Response.CheckResponse, NetworkError>

    suspend fun getAccessToken(): String?

    suspend fun getRefreshToken(): String?

    suspend fun clearTokens()
}