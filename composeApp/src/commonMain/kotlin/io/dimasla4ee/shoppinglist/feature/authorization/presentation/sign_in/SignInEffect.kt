package io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in

import io.dimasla4ee.shoppinglist.core.mvi.MviEffect

sealed interface SignInEffect : MviEffect {
    data object NavigateToMain : SignInEffect
    data object NavigateToRecoverPassword : SignInEffect
    data object NavigateToRegister : SignInEffect
}