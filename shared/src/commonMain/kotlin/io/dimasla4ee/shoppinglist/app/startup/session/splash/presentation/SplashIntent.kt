package io.dimasla4ee.shoppinglist.app.startup.session.splash.presentation

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface SplashIntent : MviIntent {

    data object Initialize : SplashIntent
}