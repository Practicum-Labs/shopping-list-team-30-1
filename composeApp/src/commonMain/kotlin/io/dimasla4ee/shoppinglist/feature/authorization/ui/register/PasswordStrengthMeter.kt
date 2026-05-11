package io.dimasla4ee.shoppinglist.feature.authorization.ui.register

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthLevel
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.password_strength_good
import shoppinglist.composeapp.generated.resources.password_strength_medium
import shoppinglist.composeapp.generated.resources.password_strength_strong
import shoppinglist.composeapp.generated.resources.password_strength_very_weak
import shoppinglist.composeapp.generated.resources.password_strength_weak

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PasswordStrengthMeter(
    level: PasswordStrengthLevel,
    modifier: Modifier = Modifier
) {
    if (level == PasswordStrengthLevel.Empty) return

    val color by animateColorAsState(
        targetValue = level.color(),
        animationSpec = tween(durationMillis = 300)
    )

    val trackColor = MaterialTheme.colorScheme.outlineVariant

    Column(modifier = modifier) {
        LinearWavyProgressIndicator(
            progress = { level.progress() },
            modifier = Modifier.fillMaxWidth(),
            color = color,
            trackColor = trackColor
        )

        Text(
            text = stringResource(level.labelRes()),
            style = MaterialTheme.typography.bodySmall,
            color = color,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
private fun PasswordStrengthLevel.color(): Color = when (this) {
    PasswordStrengthLevel.Empty -> Color.Transparent
    PasswordStrengthLevel.VeryWeak -> Color(0xFFE53935)
    PasswordStrengthLevel.Weak -> Color(0xFFD81B60)
    PasswordStrengthLevel.Medium -> Color(0xFF8E24AA)
    PasswordStrengthLevel.Good -> Color(0xFF3949AB)
    PasswordStrengthLevel.Strong -> Color(0xFF1E88E5)
}

private fun PasswordStrengthLevel.progress(): Float = when (this) {
    PasswordStrengthLevel.Empty -> 0f
    PasswordStrengthLevel.VeryWeak -> 0.2f
    PasswordStrengthLevel.Weak -> 0.4f
    PasswordStrengthLevel.Medium -> 0.6f
    PasswordStrengthLevel.Good -> 0.8f
    PasswordStrengthLevel.Strong -> 1f
}

private fun PasswordStrengthLevel.labelRes(): StringResource = when (this) {
    PasswordStrengthLevel.Empty -> Res.string.password_strength_very_weak
    PasswordStrengthLevel.VeryWeak -> Res.string.password_strength_very_weak
    PasswordStrengthLevel.Weak -> Res.string.password_strength_weak
    PasswordStrengthLevel.Medium -> Res.string.password_strength_medium
    PasswordStrengthLevel.Good -> Res.string.password_strength_good
    PasswordStrengthLevel.Strong -> Res.string.password_strength_strong
}

@Preview
@PreviewLightDark
@Composable
private fun PasswordStrengthMeterPreview() {
    ShoppingListTheme {
        Surface {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PasswordStrengthMeter(level = PasswordStrengthLevel.VeryWeak)
                PasswordStrengthMeter(level = PasswordStrengthLevel.Weak)
                PasswordStrengthMeter(level = PasswordStrengthLevel.Medium)
                PasswordStrengthMeter(level = PasswordStrengthLevel.Good)
                PasswordStrengthMeter(level = PasswordStrengthLevel.Strong)
            }
        }
    }
}