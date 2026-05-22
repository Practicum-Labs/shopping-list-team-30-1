package io.dimasla4ee.shoppinglist.app.startup.session.presentation

import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel

class SessionViewModel(
    private val authRepository: AuthRepository
) : MviViewModel<SessionIntent, SessionState, SessionEffect>(
    initialState = SessionState()
) {

    override fun reduce(
        intent: SessionIntent,
        current: SessionState
    ): SessionState = when (intent) {
        SessionIntent.LoadSession,
        SessionIntent.RefreshSession -> {
            current.copy(isLoading = true)
        }
        SessionIntent.Logout -> {
            current.copy(isLoading = true)
        }
    }

    override suspend fun handleIntent(intent: SessionIntent) {
        when (intent) {

            SessionIntent.LoadSession -> {
                loadSession()
            }

            SessionIntent.RefreshSession -> {
                loadSession()
            }

            SessionIntent.Logout -> {
                logout()
            }
        }
    }

    private suspend fun loadSession() {
        val accessToken = authRepository.getAccessToken()
        val refreshToken = authRepository.getRefreshToken()
        updateState {
            it.copy(
                isLoading = false,
                isAuthorized = refreshToken != null,
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }

    private suspend fun logout() {
        authRepository.clearTokens()
        updateState {
            SessionState()
        }
    }
}