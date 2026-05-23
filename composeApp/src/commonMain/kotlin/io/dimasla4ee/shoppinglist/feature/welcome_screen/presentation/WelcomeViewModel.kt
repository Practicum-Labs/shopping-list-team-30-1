package io.dimasla4ee.shoppinglist.feature.welcome_screen.presentation

import io.dimasla4ee.shoppinglist.core.domain.interactor.CheckSessionUseCase
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel
import kotlinx.coroutines.delay
import java.lang.System.currentTimeMillis

class WelcomeViewModel(
    private val checkSessionUseCase: CheckSessionUseCase
) : MviViewModel<WelcomeIntent, WelcomeState, WelcomeEffect>(
    initialState = WelcomeState()
) {

    companion object {
        private const val MIN_WELCOME_DURATION = 1_500L
    }

    init {
        dispatch(WelcomeIntent.CheckSession)
    }

    override fun reduce(intent: WelcomeIntent, current: WelcomeState): WelcomeState = current

    override suspend fun handleIntent(intent: WelcomeIntent) {
        when (intent) {
            WelcomeIntent.CheckSession -> {
                val startTime = currentTimeMillis()
                val authorized = checkSessionUseCase()
                val elapsed = currentTimeMillis() - startTime

                if (elapsed < MIN_WELCOME_DURATION) {
                    delay(MIN_WELCOME_DURATION - elapsed)
                }

                if (authorized) {
                    emitEffect(WelcomeEffect.NavigateToMain)
                } else {
                    emitEffect(WelcomeEffect.NavigateToAuth)
                }
            }
        }
    }
}