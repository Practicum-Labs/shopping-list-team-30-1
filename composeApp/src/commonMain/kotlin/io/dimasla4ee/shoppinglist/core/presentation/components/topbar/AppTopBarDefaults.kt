package io.dimasla4ee.shoppinglist.core.presentation.components.topbar

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppTopBarDefaults {
    @Composable
    fun appTopBarColors(
        containerColor: Color = Color.Unspecified,
        scrolledContainerColor: Color = Color.Unspecified,
        navigationIconContentColor: Color = Color.Unspecified,
        titleContentColor: Color = Color.Unspecified,
        actionIconContentColor: Color = Color.Unspecified,
        subtitleContentColor: Color = Color.Unspecified,
    ) = TopAppBarDefaults.topAppBarColors(
        containerColor = containerColor,
        scrolledContainerColor = scrolledContainerColor,
        navigationIconContentColor = navigationIconContentColor,
        titleContentColor = titleContentColor,
        actionIconContentColor = actionIconContentColor,
        subtitleContentColor = subtitleContentColor
    )

    @Composable
    fun shoppingListsTopBarColors() = appTopBarColors(
        containerColor = Color.Transparent,
        actionIconContentColor = MaterialTheme.colorScheme.onTertiary,
        titleContentColor = MaterialTheme.colorScheme.onTertiary,
        navigationIconContentColor = MaterialTheme.colorScheme.onTertiary
    )

    @Composable
    fun productsTopBarColors() = appTopBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        actionIconContentColor = MaterialTheme.colorScheme.onBackground,
        titleContentColor = MaterialTheme.colorScheme.onBackground,
        navigationIconContentColor = MaterialTheme.colorScheme.onBackground
    )
}