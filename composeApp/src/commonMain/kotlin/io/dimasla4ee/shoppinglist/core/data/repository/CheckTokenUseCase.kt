package io.dimasla4ee.shoppinglist.core.data.repository

import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository

class CheckTokenUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        accessToken: String
    ): DomainResult<Response.CheckResponse, NetworkError> {
        return authRepository.checkToken(accessToken)
    }
}