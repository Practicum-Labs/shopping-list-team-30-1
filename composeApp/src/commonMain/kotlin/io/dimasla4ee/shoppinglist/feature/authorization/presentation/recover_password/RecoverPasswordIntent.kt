package io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface RecoverPasswordIntent : MviIntent {
    sealed interface Action : RecoverPasswordIntent {
        data object RecoverPasswordClicked : Action
        data object CancelClicked : Action
        data object BackClicked : Action
    }

    sealed interface UI : RecoverPasswordIntent
}