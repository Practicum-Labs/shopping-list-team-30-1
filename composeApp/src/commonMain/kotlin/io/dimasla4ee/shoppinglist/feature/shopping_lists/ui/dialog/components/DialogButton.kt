package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.components

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DialogButton(
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        modifier = modifier
    ) {
        Text(text = text)
    }
}