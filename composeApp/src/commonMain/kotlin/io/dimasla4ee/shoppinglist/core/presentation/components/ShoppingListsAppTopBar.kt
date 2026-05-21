package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalThemeMode
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.preview.EnumSwitcherRow
import io.dimasla4ee.shoppinglist.core.utils.TestOnlyPurpose
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FabMenuFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FigmaFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.DropdownMenuTopBar
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.FabMenuTopBar
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.FigmaTopBar
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_system_theme_24
import shoppinglist.composeapp.generated.resources.ic_theme_24
import shoppinglist.composeapp.generated.resources.ic_theme_light_24

enum class ScreenVariant {
    FIGMA,
    FAB_MENU,
    DROPDOWN_MENU
}

@OptIn(TestOnlyPurpose::class)
@Composable
fun ShoppingListsScaffold(
    title: String,
    action1: TopBarAction,
    action2: TopBarAction,
    action3: TopBarAction,
    onFabClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    var screenVariant by remember { mutableStateOf(ScreenVariant.FAB_MENU) }

    val themeMode = LocalThemeMode.current
    val themeIcon = when (themeMode) {
        ThemeMode.SYSTEM -> Res.drawable.ic_system_theme_24
        ThemeMode.LIGHT -> Res.drawable.ic_theme_24
        ThemeMode.DARK -> Res.drawable.ic_theme_light_24
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            when (screenVariant) {
                ScreenVariant.FIGMA -> FigmaTopBar(title, action1, action2, action3, themeIcon)
                ScreenVariant.FAB_MENU -> FabMenuTopBar(title, action1, action3, themeIcon)
                ScreenVariant.DROPDOWN_MENU ->
                    DropdownMenuTopBar(title, action1, action2, action3, themeMode)
            }
        },
        floatingActionButton = {
            when (screenVariant) {
                ScreenVariant.FIGMA -> FigmaFloatingActionButton(onFabClick)
                ScreenVariant.FAB_MENU -> FabMenuFloatingActionButton(action2, onFabClick)
                ScreenVariant.DROPDOWN_MENU -> FigmaFloatingActionButton(onFabClick)
            }
        },
        bottomBar = {
            // TODO: Удалить после выбора реализации
            EnumSwitcherRow(
                enumEntries = ScreenVariant.entries,
                currentValue = screenVariant,
                onClick = { screenVariant = it }
            )
        },
        content = content
    )
}

data class TopBarAction(
    val contentDescription: String? = null,
    val onClick: () -> Unit
)