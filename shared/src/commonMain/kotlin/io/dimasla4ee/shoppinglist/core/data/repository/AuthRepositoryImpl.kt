package io.dimasla4ee.shoppinglist.core.data.repository

import io.dimasla4ee.shoppinglist.core.data.network.client.NetworkClient
import io.dimasla4ee.shoppinglist.core.data.network.client.toNetworkError
import io.dimasla4ee.shoppinglist.core.data.network.dto.Request
import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val networkClient: NetworkClient
) : AuthRepository {

    override suspend fun register(
        email: String,
        password: String
    ): DomainResult<Response.RegisterResponse, NetworkError> {
        val request = Request.RegisterRequest(email, password)

        return networkClient
            .doRequest<Response.RegisterResponse>(request)
            .toDomainResult { it }
    }

    override suspend fun login(
        email: String,
        password: String
    ): DomainResult<Response.UserAuthResponse, NetworkError> {
        val request = Request.UserAuthRequest(email, password)

        return networkClient
            .doRequest<Response.UserAuthResponse>(request)
            .toDomainResult { it }
    }

    override suspend fun refresh(
        refreshToken: String
    ): DomainResult<Response.RefreshTokenResponse, NetworkError> {
        val request = Request.RefreshTokenRequest(refreshToken)

        return networkClient
            .doRequest<Response.RefreshTokenResponse>(request)
            .toDomainResult { it }
    }

    override suspend fun recoverPassword(
        email: String
    ): DomainResult<Response.RecoverPasswordResponse, NetworkError> {
        val request = Request.RecoverPasswordRequest(email)

        return networkClient
            .doRequest<Response.RecoverPasswordResponse>(request)
            .toDomainResult { it }
    }

    override suspend fun checkToken(
        accessToken: String
    ): DomainResult<Response.CheckResponse, NetworkError> {
        val request = Request.CheckUserRequest(
            authorization = "Bearer $accessToken"
        )

        return networkClient
            .doRequest<Response.CheckResponse>(request)
            .toDomainResult { it }
    }

    private suspend inline fun <T : Response, D> Result<T>.toDomainResult(
        mapper: (T) -> D
    ): DomainResult<D, NetworkError> {
        return fold(
            onSuccess = { response ->
                DomainResult.Success(mapper(response))
            },
            onFailure = { throwable ->
                DomainResult.Error(throwable.toNetworkError())
            }
        )
    }
}