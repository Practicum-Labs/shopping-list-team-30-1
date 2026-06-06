package io.dimasla4ee.shoppinglist.core.domain.interactor.token

import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage

class SaveAuthTokensUseCase(
    private val tokenStorage: TokenStorage
) {
    suspend operator fun invoke(
        accessToken: String,
        refreshToken: String
    ) {
        tokenStorage.saveTokens(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}