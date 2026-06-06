package io.dimasla4ee.shoppinglist.feature.welcome_screen.presentation

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface WelcomeIntent : MviIntent {

    data object CheckSession : WelcomeIntent
}