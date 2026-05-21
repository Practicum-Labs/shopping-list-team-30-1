package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import io.dimasla4ee.shoppinglist.core.presentation.preview.EnumSwitcherRow
import io.dimasla4ee.shoppinglist.core.utils.TestOnlyPurpose
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FabMenuFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FigmaFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.DropdownMenuTopBar
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.FigmaTopBar

private enum class ScreenVariant {
    FIGMA,
    FAB_MENU,
    DROPDOWN_MENU
}

@OptIn(TestOnlyPurpose::class)
@Composable
fun ShoppingListsScaffold(
    title: String,
    onSearchClick: ActionItem,
    onDeleteAllClick: ActionItem,
    onThemeSwitch: ActionItem,
    onAddListClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    var screenVariant by remember { mutableStateOf(ScreenVariant.FAB_MENU) }

    Scaffold(
        modifier = modifier,
        topBar = {
            when (screenVariant) {
                ScreenVariant.FIGMA -> FigmaTopBar(
                    title = title,
                    onSearchClick = onSearchClick,
                    onDeleteAllClick = onDeleteAllClick,
                    onThemeSwitch = onThemeSwitch
                )

                ScreenVariant.FAB_MENU -> FigmaTopBar(
                    title = title,
                    onSearchClick = onSearchClick,
                    onThemeSwitch = onThemeSwitch
                )

                ScreenVariant.DROPDOWN_MENU -> DropdownMenuTopBar(
                    title = title,
                    onSearchClick = onSearchClick,
                    onDeleteAllClick = onDeleteAllClick,
                    onThemeSwitch = onThemeSwitch,
                    themeMode = LocalThemeMode.current
                )
            }
        },
        floatingActionButton = {
            when (screenVariant) {
                ScreenVariant.FIGMA -> FigmaFloatingActionButton(
                    onAddListClick = onAddListClick
                )

                ScreenVariant.FAB_MENU -> FabMenuFloatingActionButton(
                    onDeleteAllClick = onDeleteAllClick,
                    onAddListClick = onAddListClick
                )

                ScreenVariant.DROPDOWN_MENU -> FigmaFloatingActionButton(
                    onAddListClick = onAddListClick
                )
            }
        },
        bottomBar = {
            EnumSwitcherRow(
                enumEntries = ScreenVariant.entries,
                currentValue = screenVariant,
                onClick = { screenVariant = it }
            )
        },
        content = content
    )
}