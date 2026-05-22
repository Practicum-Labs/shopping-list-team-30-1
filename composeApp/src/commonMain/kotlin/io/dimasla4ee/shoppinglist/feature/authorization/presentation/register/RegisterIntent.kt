package io.dimasla4ee.shoppinglist.feature.authorization.presentation.register

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface RegisterIntent : MviIntent {
    sealed interface UI : RegisterIntent {
        data object PasswordVisibilityToggleClicked : UI
        data object ConfirmPasswordVisibilityToggleClicked : UI
    }

    sealed interface Action : RegisterIntent {
        data object RegisterClicked : Action
        data object SignInClicked : Action
    }
}