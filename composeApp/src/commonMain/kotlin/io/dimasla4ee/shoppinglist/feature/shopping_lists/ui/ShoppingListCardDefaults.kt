package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui

import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ShoppingListCardDefaults {
    @Composable
    fun shoppingListCardColors(
        containerColor: Color = MaterialTheme.colorScheme.background,
        contentColor: Color = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified
    ): CardColors = CardColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun shoppingListCardElevation(
        defaultElevation: Dp = ShoppingListCardTokens.ContainerElevation,
        pressedElevation: Dp = ShoppingListCardTokens.PressedContainerElevation,
        focusedElevation: Dp = ShoppingListCardTokens.FocusContainerElevation,
        hoveredElevation: Dp = ShoppingListCardTokens.HoverContainerElevation,
        draggedElevation: Dp = ShoppingListCardTokens.DraggedContainerElevation,
        disabledElevation: Dp = ShoppingListCardTokens.DisabledContainerElevation,
    ): CardElevation = CardDefaults.cardElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        draggedElevation = draggedElevation,
        disabledElevation = disabledElevation
    )

    private object ShoppingListCardTokens {
        val DisabledContainerElevation: Dp = 0.dp
        val DraggedContainerElevation: Dp = 0.dp
        val FocusContainerElevation: Dp = 0.dp
        val HoverContainerElevation: Dp = 0.dp
        val PressedContainerElevation: Dp = 0.dp
        val ContainerElevation: Dp = 4.dp
    }
}