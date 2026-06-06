package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui

import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppIconButtonDefaults {
    @Composable
    fun appIconButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
        disabledContainerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
        disabledContentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer
    ): IconButtonColors = IconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}