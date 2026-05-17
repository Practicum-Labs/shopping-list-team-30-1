package io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface SignInIntent: MviIntent {
    data object TogglePassword: SignInIntent
    data object SignIn: SignInIntent
    data object SignUpClicked: SignInIntent
    data object ForgotPasswordClicked: SignInIntent
}