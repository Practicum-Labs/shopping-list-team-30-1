package io.dimasla4ee.shoppinglist.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun IconAction(
    iconRes: DrawableResource,
    contentDescription: String? = null,
    onClick: (() -> Unit)? = null
) {
    IconButton(
        colors = IconActionDefaults.iconActionColors(),
        onClick = { onClick?.invoke() },
        enabled = onClick != null
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription
        )
    }
}

object IconActionDefaults {
    @Composable
    fun iconActionColors(): IconButtonColors = IconButtonColors(
        containerColor = Color(0xFFEEE0D5),
        contentColor = Color(0xFF845416),
        disabledContainerColor = Color(0xFFFEDDBD),
        disabledContentColor = Color(0xFF281805)
    )
}