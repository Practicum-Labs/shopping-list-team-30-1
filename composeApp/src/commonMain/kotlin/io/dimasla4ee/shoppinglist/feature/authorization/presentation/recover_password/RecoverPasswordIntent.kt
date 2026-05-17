package io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface RecoverPasswordIntent: MviIntent {
    data object RecoverPassword: RecoverPasswordIntent
    data object CancelClicked: RecoverPasswordIntent
}