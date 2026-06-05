package io.dimasla4ee.shoppinglist.core.domain.interactor.token

import io.dimasla4ee.shoppinglist.app.startup.session.presentation.SessionState
import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

class ObserveSessionUseCase(
    private val tokenStorage: TokenStorage
) {
    operator fun invoke(): Flow<SessionState> {
        return combine(
            tokenStorage.observeAccessToken(),
            tokenStorage.observeRefreshToken()
        ) { accessToken, refreshToken ->
            SessionState(
                accessToken = accessToken,
                refreshToken = refreshToken,
                isAuthorized = !refreshToken.isNullOrBlank()
            )
        }.distinctUntilChanged()
    }
}