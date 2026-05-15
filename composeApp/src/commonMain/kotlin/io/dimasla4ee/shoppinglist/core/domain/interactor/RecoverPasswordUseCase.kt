package io.dimasla4ee.shoppinglist.core.domain.interactor

import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository

class RecoverPasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): DomainResult<Response.RecoverPasswordResponse, NetworkError> {
        return authRepository.recoverPassword()
    }
}