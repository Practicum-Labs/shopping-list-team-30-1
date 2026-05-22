package io.dimasla4ee.shoppinglist.app.startup.session.splash.presentation

import io.dimasla4ee.shoppinglist.app.startup.session.domain.AppLaunchRepository
import io.dimasla4ee.shoppinglist.app.startup.session.presentation.SessionIntent
import io.dimasla4ee.shoppinglist.app.startup.session.presentation.SessionViewModel
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel

class SplashViewModel(
    private val appLaunchRepository: AppLaunchRepository,
    private val sessionViewModel: SessionViewModel
) : MviViewModel<SplashIntent, SplashState, SplashEffect>(
    initialState = SplashState()
) {

    override fun reduce(
        intent: SplashIntent,
        current: SplashState
    ): SplashState = when (intent) {

        SplashIntent.Initialize -> {
            current.copy(isLoading = true)
        }
    }

    override suspend fun handleIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.Initialize -> {
                initialize()
            }
        }
    }

    private suspend fun initialize() {
        sessionViewModel.dispatch(
            SessionIntent.LoadSession
        )
        val isFirstLaunch = appLaunchRepository.isFirstLaunch()
        val effect = when {
            isFirstLaunch -> {
                SplashEffect.NavigateToWelcome
            }

            else -> {
                SplashEffect.NavigateToShoppingLists
            }
        }

        emitEffect(effect)
        updateState {
            it.copy(isLoading = false)
        }
    }
}