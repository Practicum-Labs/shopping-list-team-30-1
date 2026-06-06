package io.dimasla4ee.shoppinglist.app.startup.session.presentation

import androidx.lifecycle.viewModelScope
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.ClearAuthTokensUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.ObserveSessionUseCase
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SessionViewModel(
    private val observeSessionUseCase: ObserveSessionUseCase,
    private val clearAuthTokensUseCase: ClearAuthTokensUseCase
) : MviViewModel<SessionIntent, SessionState, SessionEffect>(
    initialState = SessionState(isLoading = true)
) {

    override fun reduce(
        intent: SessionIntent,
        current: SessionState
    ): SessionState = when (intent) {
        SessionIntent.LoadSession,
        SessionIntent.RefreshSession -> current.copy(isLoading = true)

        SessionIntent.Logout -> current.copy(isLoading = true)
    }

    override suspend fun handleIntent(intent: SessionIntent) {
        when (intent) {
            SessionIntent.LoadSession,
            SessionIntent.RefreshSession -> Unit

            SessionIntent.Logout -> logout()
        }
    }

    fun observeSession() {
        viewModelScope.launch {
            observeSessionUseCase().collectLatest { session ->
                updateState {
                    it.copy(
                        isLoading = false,
                        isAuthorized = session.isAuthorized,
                        accessToken = session.accessToken,
                        refreshToken = session.refreshToken
                    )
                }
            }
        }
    }

    private suspend fun logout() {
        clearAuthTokensUseCase()
        updateState { SessionState() }
    }
}