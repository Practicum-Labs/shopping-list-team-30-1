package io.dimasla4ee.shoppinglist.core.domain.interactor.token

import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository
import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage

class CheckTokenUseCase(
    private val authRepository: AuthRepository,
    private val tokenStorage: TokenStorage
) {
    suspend operator fun invoke(): DomainResult<Response.CheckResponse, NetworkError> {
        val accessToken = tokenStorage.getAccessToken()
            ?: return DomainResult.Success(
                Response.CheckResponse(
                    success = false,
                    refresh = false
                )
            )

        return authRepository.checkToken(accessToken)
    }
}