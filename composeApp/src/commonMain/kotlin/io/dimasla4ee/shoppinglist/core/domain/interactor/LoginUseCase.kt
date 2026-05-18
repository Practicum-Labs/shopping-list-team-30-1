package io.dimasla4ee.shoppinglist.core.domain.interactor

import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository

@Suppress("ForbiddenComment")
class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): DomainResult<Response.UserAuthResponse, NetworkError> {
        // TODO: Сохранение токенов в локальное хранилище после успешной авторизации
        return authRepository.login(email, password)
    }
}