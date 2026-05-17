package io.dimasla4ee.shoppinglist.feature.authorization.presentation.register

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface RegisterIntent: MviIntent {
    data object TogglePassword: RegisterIntent
    data object Register: RegisterIntent
    data object SignInClicked: RegisterIntent
}