package io.dimasla4ee.shoppinglist.feature.authorization.presentation.register

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface RegisterIntent : MviIntent {
    data object PasswordVisibilityToggleClicked : RegisterIntent
    data object RegisterClicked : RegisterIntent
    data object SignInClicked : RegisterIntent
}