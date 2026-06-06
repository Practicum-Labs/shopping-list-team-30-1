package io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface SignInIntent : MviIntent {
    sealed interface UI : SignInIntent {
        data object PasswordVisibilityToggleClicked : UI
    }

    sealed interface Action : SignInIntent {
        data object SignInClicked : Action
        data object ForgotPasswordClicked : Action
        data object SignUpClicked : Action
        data object ContinueAsGuestClicked : Action
        data object BackClicked : Action
    }
}