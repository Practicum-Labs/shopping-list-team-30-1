package io.dimasla4ee.shoppinglist.app.startup.session.splash.presentation

import io.dimasla4ee.shoppinglist.app.startup.session.splash.domain.SplashDestination
import io.dimasla4ee.shoppinglist.core.mvi.MviState

data class SplashState(
    val isLoading: Boolean = true,
    val destination: SplashDestination? = null
) : MviState