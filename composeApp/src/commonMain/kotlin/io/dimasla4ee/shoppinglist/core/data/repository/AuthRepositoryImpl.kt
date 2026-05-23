package io.dimasla4ee.shoppinglist.core.data.repository

import io.dimasla4ee.shoppinglist.core.data.network.client.NetworkClient
import io.dimasla4ee.shoppinglist.core.data.network.client.toNetworkError
import io.dimasla4ee.shoppinglist.core.data.network.dto.Request
import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository
import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage

class AuthRepositoryImpl(
    private val networkClient: NetworkClient,
    private val tokenStorage: TokenStorage
) : AuthRepository {

    override suspend fun register(
        email: String,
        password: String
    ): DomainResult<Response.RegisterResponse, NetworkError> {
        val request = Request.RegisterRequest(email, password)

        val result = networkClient.doRequest<Response.RegisterResponse>(request)

        return result.toDomainResult { response ->
            tokenStorage.saveTokens(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken
            )

            response
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): DomainResult<Response.UserAuthResponse, NetworkError> {

        val request = Request.UserAuthRequest(email, password)

        val result = networkClient.doRequest<Response.UserAuthResponse>(request)

        return result.toDomainResult { response ->
            tokenStorage.saveTokens(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken
            )

            response
        }
    }

    override suspend fun refresh(
        refreshToken: String
    ): DomainResult<Response.RefreshTokenResponse, NetworkError> {

        val request = Request.RefreshTokenRequest(refreshToken)

        val result = networkClient.doRequest<Response.RefreshTokenResponse>(request)

        return result.toDomainResult { response ->
            tokenStorage.saveTokens(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken
            )

            response
        }
    }

    override suspend fun recoverPassword(
        email: String
    ): DomainResult<Response.RecoverPasswordResponse, NetworkError> {
        val request = Request.RecoverPasswordRequest(email)

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
        mapper: suspend (T) -> D
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

    override suspend fun getAccessToken(): String? {
        return tokenStorage.getAccessToken()
    }

    override suspend fun getRefreshToken(): String? {
        return tokenStorage.getRefreshToken()
    }

    override suspend fun clearTokens() {
        tokenStorage.clearTokens()
    }
}