package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppOutlinedTextFieldDefaults {
    @Composable
    fun colors(
        focusedLabelColor: Color = MaterialTheme.colorScheme.secondary,
        focusedBorderColor: Color = MaterialTheme.colorScheme.secondary
    ): TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedLabelColor = focusedLabelColor,
        focusedBorderColor = focusedBorderColor
    )
}