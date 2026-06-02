package io.dimasla4ee.shoppinglist.core.presentation.components.fab_menu

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
object AppFloatingActionButtonMenu {

    @Composable
    fun iconColor(
        initialColor: Color = MaterialTheme.colorScheme.onPrimary,
        finalColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
    ): (Float) -> Color = ToggleFloatingActionButtonDefaults.iconColor(
        initialColor = initialColor,
        finalColor = finalColor
    )

    @Composable
    fun containerColor(
        initialColor: Color = MaterialTheme.colorScheme.primary,
        finalColor: Color = MaterialTheme.colorScheme.primaryContainer
    ) = ToggleFloatingActionButtonDefaults.containerColor(
        initialColor = initialColor,
        finalColor = finalColor
    )

}