package io.dimasla4ee.shoppinglist.feature.welcome_screen.presentation

import io.dimasla4ee.shoppinglist.core.mvi.MviState

data class WelcomeState(
    val isLoading: Boolean = true
) : MviState
