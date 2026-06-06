package io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password

import io.dimasla4ee.shoppinglist.core.mvi.MviEffect

sealed interface RecoverPasswordEffect : MviEffect {
    data object NavigateToSignIn : RecoverPasswordEffect
    data object NavigateBack : RecoverPasswordEffect
}