package io.dimasla4ee.shoppinglist.core.presentation.components.buttons

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppButtonDefaults {
    @Composable
    fun appTextButtonColors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = MaterialTheme.colorScheme.secondary,
        disabledContainerColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified
    ): ButtonColors = ButtonDefaults.textButtonColors(
        containerColor = containerColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        contentColor = contentColor
    )
}