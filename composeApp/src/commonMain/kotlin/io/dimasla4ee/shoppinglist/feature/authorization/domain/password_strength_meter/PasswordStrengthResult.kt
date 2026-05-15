package io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter

data class PasswordStrengthResult(
    val score: Int,
    val level: PasswordStrengthLevel,
    val isAcceptable: Boolean
)