package io.dimasla4ee.shoppinglist.core.data.repository

import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.data.network.client.NetworkClient
import io.dimasla4ee.shoppinglist.core.data.network.client.toNetworkError
import io.dimasla4ee.shoppinglist.core.data.network.dto.Request
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val networkClient: NetworkClient
) : AuthRepository {

    override suspend fun register(
        email: String,
        password: String
    ): DomainResult<Response.RegisterResponse, NetworkError> {
        val request = Request.RegisterRequest(email = email, password = password)
        return networkClient
            .doRequest<Response.RegisterResponse>(request)
            .toDomainResult { response ->
                Response.RegisterResponse(
                    userId = response.userId,
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken
                )
            }
    }

    override suspend fun login(
        email: String,
        password: String
    ): DomainResult<Response.UserAuthResponse, NetworkError> {
        val request = Request.UserAuthRequest(email = email, password = password)
        return networkClient
            .doRequest<Response.UserAuthResponse>(request)
            .toDomainResult { response ->
                Response.UserAuthResponse(
                    userId = response.userId,
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken
                )
            }
    }

    override suspend fun refresh(
        refreshToken: String
    ): DomainResult<Response.RefreshTokenResponse, NetworkError> {
        val request = Request.RefreshTokenRequest(refreshToken = refreshToken)
        return networkClient
            .doRequest<Response.RefreshTokenResponse>(request)
            .toDomainResult { response ->
                Response.RefreshTokenResponse(
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken
                )
            }
    }

    override suspend fun recoverPassword(): DomainResult<Response.RecoverPasswordResponse, NetworkError> {
        val request = Request.RecoverPasswordRequest
        return networkClient
            .doRequest<Response.RecoverPasswordResponse>(request)
            .toDomainResult { response ->
                Response.RecoverPasswordResponse(
                    message = response.message
                )
            }
    }

    override suspend fun checkToken(
        accessToken: String
    ): DomainResult<Response.CheckResponse, NetworkError> {
        val request = Request.CheckUserRequest(authorization = "Bearer $accessToken")
        return networkClient
            .doRequest<Response.CheckResponse>(request)
            .toDomainResult { response ->
                Response.CheckResponse(
                    success = response.success,
                    refresh = response.refresh
                )
            }
    }

    /**
     * Преобразует [Result]<[Response]> в [DomainResult]<[D], [NetworkError]>.
     *
     * @param mapper маппинг из сетевого DTO в доменную модель.
     */
    private suspend fun <T : Response, D> Result<T>.toDomainResult(
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