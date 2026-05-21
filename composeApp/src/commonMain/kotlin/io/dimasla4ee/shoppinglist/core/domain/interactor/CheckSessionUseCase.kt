package io.dimasla4ee.shoppinglist.core.domain.interactor

import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository

class CheckSessionUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {

        val accessToken = authRepository.getAccessToken() ?: return false

        when (authRepository.checkToken(accessToken)
        ) {
            is DomainResult.Success -> {
                return true
            }

            is DomainResult.Error -> {
                val refreshToken = authRepository.getRefreshToken() ?: return false

                return when (
                    authRepository.refresh(refreshToken)
                ) {
                    is DomainResult.Success -> {
                        true
                    }
                    is DomainResult.Error -> {
                        authRepository.clearTokens()
                        false
                    }
                }
            }
        }
    }
}