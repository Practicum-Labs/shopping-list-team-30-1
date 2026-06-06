package io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter

object PasswordStrengthMapper {

    fun fromScore(score: Int, password: String): PasswordStrengthResult {
        if (password.isEmpty()) {
            return PasswordStrengthResult(
                score = 0,
                level = PasswordStrengthLevel.Empty,
                isAcceptable = false
            )
        }

        val level = when (score) {
            0 -> PasswordStrengthLevel.VeryWeak
            1 -> PasswordStrengthLevel.Weak
            2 -> PasswordStrengthLevel.Medium
            3 -> PasswordStrengthLevel.Good
            else -> PasswordStrengthLevel.Strong
        }

        val isAcceptable = score >= 2 && password.length >= 8

        return PasswordStrengthResult(
            score = score,
            level = level,
            isAcceptable = isAcceptable
        )
    }
}