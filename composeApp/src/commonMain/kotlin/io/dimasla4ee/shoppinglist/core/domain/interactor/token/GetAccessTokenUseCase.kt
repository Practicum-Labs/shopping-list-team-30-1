package io.dimasla4ee.shoppinglist.core.domain.interactor.token

import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage

class GetAccessTokenUseCase(
    private val tokenStorage: TokenStorage
) {
    suspend operator fun invoke(): String? {
        return tokenStorage.getAccessToken()
    }
}