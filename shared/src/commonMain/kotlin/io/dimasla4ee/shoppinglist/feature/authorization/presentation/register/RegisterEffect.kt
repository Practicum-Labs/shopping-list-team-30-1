package io.dimasla4ee.shoppinglist.feature.authorization.presentation.register

import io.dimasla4ee.shoppinglist.core.mvi.MviEffect

sealed interface RegisterEffect : MviEffect {
    data object NavigateToMain : RegisterEffect
    data object NavigateToSignIn : RegisterEffect
    data object NavigateBack : RegisterEffect
}