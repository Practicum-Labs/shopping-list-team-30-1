package io.dimasla4ee.shoppinglist.feature.welcome_screen.presentation

import io.dimasla4ee.shoppinglist.core.mvi.MviEffect

sealed interface WelcomeEffect : MviEffect {

    data object NavigateToMain : WelcomeEffect

    data object NavigateToAuth : WelcomeEffect
}