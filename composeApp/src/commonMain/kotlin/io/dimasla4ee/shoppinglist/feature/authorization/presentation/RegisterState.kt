package io.dimasla4ee.shoppinglist.feature.authorization.presentation

import androidx.compose.foundation.text.input.TextFieldState
import io.dimasla4ee.shoppinglist.core.mvi.MviState

data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val isRegisterAllowed: Boolean = false,
    val passwordHasSpecialCharacter: Boolean = false,
    val passwordIsLongEnough: Boolean = false,
    val passwordHasNumber: Boolean = false
) : MviState