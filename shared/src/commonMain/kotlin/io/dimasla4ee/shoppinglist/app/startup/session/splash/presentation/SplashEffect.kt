package io.dimasla4ee.shoppinglist.app.startup.session.splash.presentation

import io.dimasla4ee.shoppinglist.core.mvi.MviEffect

sealed interface SplashEffect : MviEffect {

    data object NavigateToWelcome : SplashEffect
    data object NavigateToShoppingLists : SplashEffect
}