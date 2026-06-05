package io.dimasla4ee.shoppinglist.core.domain.interactor.token

import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage

class ClearAuthTokensUseCase(
    private val tokenStorage: TokenStorage
) {
    suspend operator fun invoke() {
        tokenStorage.clearTokens()
    }
}


