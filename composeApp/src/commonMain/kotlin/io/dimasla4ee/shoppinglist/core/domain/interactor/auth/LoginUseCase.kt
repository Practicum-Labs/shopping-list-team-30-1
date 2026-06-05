package io.dimasla4ee.shoppinglist.core.domain.interactor.auth

import io.dimasla4ee.shoppinglist.core.domain.interactor.token.SaveAuthTokensUseCase
import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val saveAuthTokensUseCase: SaveAuthTokensUseCase
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): DomainResult<Response.UserAuthResponse, NetworkError> {
        return when (val result = authRepository.login(email, password)) {
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