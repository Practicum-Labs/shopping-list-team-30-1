package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.components.topbar.AppTopBarDefaults
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_delete_24
import shoppinglist.composeapp.generated.resources.ic_menu_24
import shoppinglist.composeapp.generated.resources.ic_search_24

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuTopBar(
    title: String,
    onSearchClick: ActionItem,
    onDeleteAllClick: ActionItem,
    onThemeSwitch: ActionItem,
    themeMode: ThemeMode,
    modifier: Modifier = Modifier,
    onAuthorizationClick: ActionItem
) {
    var menuIsExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        colors = AppTopBarDefaults.shoppingListsTopBarColors(),
        modifier = modifier.padding(end = AppDimensions.paddingVerySmall),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        actions = {
            IconButton(onClick = onSearchClick.onClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_search_24),
                    contentDescription = onSearchClick.label,
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }

            IconButton(onClick = { menuIsExpanded = !menuIsExpanded }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_menu_24),
                    contentDescription = "More options"
                )
            }

            DropdownMenu(
                expanded = menuIsExpanded,
                onDismissRequest = { menuIsExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(onAuthorizationClick.label ?: "") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(onAuthorizationClick.iconRes ?: Res.drawable.ic_menu_24),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        menuIsExpanded = false
                        onAuthorizationClick.onClick()
                    }
                )

                HorizontalDivider()

                DropdownMenuItem(
                    text = { Text("Удалить всё") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(
                                Res.drawable.ic_delete_24
                            ),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        menuIsExpanded = false
                        onDeleteAllClick.onClick()
                    }
                )

                HorizontalDivider()

                ThemeMode.entries.forEach {
                    DropdownMenuItem(
                        text = { Text(it.name) },
                        leadingIcon = {
                            RadioButton(
                                selected = it == themeMode,
                                onClick = null
                            )
                        },
                        onClick = {
                            menuIsExpanded = false
                            onThemeSwitch.onClick
                        }
                    )
                }
            }
        }
    )
}