package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.components

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        colors = colors
    ) {
        Text(text = text)
    }
}