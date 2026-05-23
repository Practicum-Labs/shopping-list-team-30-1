package io.dimasla4ee.shoppinglist.core.domain.interactor.token

import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository
import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage

class RefreshSessionUseCase(
    private val authRepository: AuthRepository,
    private val tokenStorage: TokenStorage,
    private val saveAuthTokensUseCase: SaveAuthTokensUseCase
) {
    suspend operator fun invoke(): DomainResult<Response.RefreshTokenResponse, NetworkError> {
        val refreshToken = tokenStorage.getRefreshToken()
            ?: return DomainResult.Error(
                NetworkError.Unauthorized("Refresh token отсутствует")
            )

        return when (val result = authRepository.refresh(refreshToken)) {
            is DomainResult.Success -> {
                saveAuthTokensUseCase(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken
                )
                result
            }

            is DomainResult.Error -> result
        }
    }
}