package io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface SignInIntent : MviIntent {
    data object PasswordVisibilityToggleClicked : SignInIntent
    data object SignInClicked : SignInIntent
    data object ForgotPasswordClicked : SignInIntent
    data object SignUpClicked : SignInIntent
}