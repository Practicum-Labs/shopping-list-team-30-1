package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.core.presentation.components.topbar.AppTopBar
import io.dimasla4ee.shoppinglist.core.presentation.components.topbar.AppTopBarDefaults
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem

@Composable
fun FigmaTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onSearchClick: ActionItem? = null,
    onDeleteAllClick: ActionItem? = null,
    onThemeSwitch: ActionItem? = null
) {
    val actions: List<ActionItem> = listOfNotNull(onSearchClick, onDeleteAllClick, onThemeSwitch)

    AppTopBar(
        title = title,
        modifier = modifier.padding(end = AppDimensions.paddingVerySmall),
        actions = actions,
        colors = AppTopBarDefaults.shoppingListsTopBarColors()
    )
}

