package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalThemeMode
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FabMenuFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FigmaFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.DropdownMenuTopBar
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.FabMenuTopBar
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.FigmaTopBar
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_system_theme_24
import shoppinglist.composeapp.generated.resources.ic_theme_24
import shoppinglist.composeapp.generated.resources.ic_theme_light_24
import java.util.Locale

enum class ScreenVariant {
    FIGMA,
    FAB_MENU,
    DROPDOWN_MENU
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
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
            Surface(
                tonalElevation = 3.dp,
                shadowElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ScreenVariant.entries.forEach { variant ->
                        val isSelected = screenVariant == variant
                        AssistChip(
                            onClick = { screenVariant = variant },
                            label = { Text(variant.name, style = MaterialTheme.typography.labelSmall) },
                            modifier = Modifier.padding(horizontal = 4.dp),
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.surfaceVariant,
                                labelColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                                else MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            border = if (isSelected) null else null
                        )
                    }
                }
            }
        },
        content = content
    )
}

data class TopBarAction(
    val contentDescription: String? = null,
    val onClick: () -> Unit
)