package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui

import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
object AppIconButtonDefaults {
    @Composable
    fun iconActionColors(
        containerColor: Color = Color(0xFFEEE0D5),
        contentColor: Color = Color(0xFF845416),
        disabledContainerColor: Color = Color(0xFFFEDDBD),
        disabledContentColor: Color = Color(0xFF281805)
    ): IconButtonColors = IconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}