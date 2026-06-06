package io.dimasla4ee.shoppinglist.feature.authorization.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalAppColors
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthLevel
import org.jetbrains.compose.resources.StringResource
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.password_strength_good
import shoppinglist.shared.generated.resources.password_strength_medium
import shoppinglist.shared.generated.resources.password_strength_strong
import shoppinglist.shared.generated.resources.password_strength_very_weak
import shoppinglist.shared.generated.resources.password_strength_weak

fun PasswordStrengthLevel.progress(): Float {
    val maxLevel = PasswordStrengthLevel.Strong.ordinal.toFloat()
    return ordinal / maxLevel
}

fun PasswordStrengthLevel.labelRes(): StringResource? = when (this) {
    PasswordStrengthLevel.Empty -> null
    PasswordStrengthLevel.VeryWeak -> Res.string.password_strength_very_weak
    PasswordStrengthLevel.Weak -> Res.string.password_strength_weak
    PasswordStrengthLevel.Medium -> Res.string.password_strength_medium
    PasswordStrengthLevel.Good -> Res.string.password_strength_good
    PasswordStrengthLevel.Strong -> Res.string.password_strength_strong
}

@Composable
@ReadOnlyComposable
fun PasswordStrengthLevel.color(): Color = when (this) {
    PasswordStrengthLevel.Empty -> Color.Transparent
    PasswordStrengthLevel.VeryWeak -> LocalAppColors.current.passwordMeterVeryWeak
    PasswordStrengthLevel.Weak -> LocalAppColors.current.passwordMeterWeak
    PasswordStrengthLevel.Medium -> LocalAppColors.current.passwordMeterMedium
    PasswordStrengthLevel.Good -> LocalAppColors.current.passwordMeterGood
    PasswordStrengthLevel.Strong -> LocalAppColors.current.passwordMeterStrong
}