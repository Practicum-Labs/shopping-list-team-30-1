package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui

import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppIconButtonDefaults {
    @Composable
    fun appIconButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.primaryContainer,             // 0xFFEEE0D5
        contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,             // 0xFF845416
        disabledContainerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,    // 0xFFFEDDBD
        disabledContentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer     // 0xFF281805
    ): IconButtonColors = IconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}