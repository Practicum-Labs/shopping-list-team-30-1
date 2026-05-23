package io.dimasla4ee.shoppinglist.feature.authorization.ui.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthLevel
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.color
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.labelRes
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.progress
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PasswordStrengthMeter(
    level: PasswordStrengthLevel,
    modifier: Modifier = Modifier
) {
    val isPasswordNotEmpty = level != PasswordStrengthLevel.Empty

    AnimatedVisibility(
        visible = isPasswordNotEmpty,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        val label = level.labelRes()?.let { stringResource(it) } ?: return@AnimatedVisibility
        val progress = level.progress()
        val trackColor = MaterialTheme.colorScheme.outlineVariant
        val color by animateColorAsState(
            targetValue = level.color(),
            animationSpec = tween(durationMillis = 300)
        )

        Column(modifier = modifier) {
            LinearWavyProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth(),
                color = color,
                trackColor = trackColor
            )

            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = color,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
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