package io.dimasla4ee.shoppinglist.core.presentation.model

import org.jetbrains.compose.resources.DrawableResource

data class ActionItem(
    val iconRes: DrawableResource? = null,
    val label: String? = null,
    val onClick: () -> Unit
)