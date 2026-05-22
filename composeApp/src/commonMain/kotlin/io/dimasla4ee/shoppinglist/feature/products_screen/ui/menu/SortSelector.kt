package io.dimasla4ee.shoppinglist.feature.products_screen.ui.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.Green500
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.btm_menu_sorting
import shoppinglist.composeapp.generated.resources.ic_arrow_bs
import shoppinglist.composeapp.generated.resources.ic_drag_pan_24
import shoppinglist.composeapp.generated.resources.ic_sort_by_alpha_24
import shoppinglist.composeapp.generated.resources.ic_swap_vert_24
import shoppinglist.composeapp.generated.resources.sort_alphabetical
import shoppinglist.composeapp.generated.resources.sort_custom

@Composable
fun SortSelector(
    sortMode: SortMode,
    onSortSelect: (SortMode) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_swap_vert_24),
                contentDescription = null
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(Res.string.btm_menu_sorting),
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = when (sortMode) {
                        SortMode.CUSTOM -> stringResource(Res.string.sort_custom)
                        SortMode.ALPHABETICAL -> stringResource(Res.string.sort_alphabetical)
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = if (expanded) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        Green500
                    }
                )
            }

            Icon(
                painter = painterResource(Res.drawable.ic_arrow_bs),
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(Res.string.sort_alphabetical)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_sort_by_alpha_24),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    RadioButton(
                        selected = sortMode == SortMode.ALPHABETICAL,
                        onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.secondary,
                            unselectedColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                },
                onClick = {
                    expanded = false
                    onSortSelect(SortMode.ALPHABETICAL)
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(Res.string.sort_custom)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_drag_pan_24),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    RadioButton(
                        selected = sortMode == SortMode.CUSTOM,
                        onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.secondary,
                            unselectedColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                },
                onClick = {
                    expanded = false
                    onSortSelect(SortMode.CUSTOM)
                }
            )
        }
    }
}