package io.dimasla4ee.shoppinglist.core.presentation.components.buttons

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun AppTextButton(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    textDecoration: TextDecoration = TextDecoration.Underline,
    colors: ButtonColors = AppButtonDefaults.appTextButtonColors(),
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = colors
    ) {
        Text(
            text = text,
            textDecoration = textDecoration,
            style = textStyle
        )
    }
}