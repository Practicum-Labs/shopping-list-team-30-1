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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_add_circle_24
import shoppinglist.composeapp.generated.resources.ic_check_24
import shoppinglist.composeapp.generated.resources.ic_drag_pan_24
import shoppinglist.composeapp.generated.resources.ic_sort_by_alpha_24
import shoppinglist.composeapp.generated.resources.ic_swap_vert_24

@Composable
fun SortSelector(
    sortMode: SortMode,
    onSortSelected: (SortMode) -> Unit,
    modifier: Modifier = Modifier
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = true
                }
                .padding(horizontal = 16.dp, vertical = 12.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(
                    Res.drawable.ic_swap_vert_24
                ),
                contentDescription = null
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Сортировка",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = when(sortMode) {
                        SortMode.CUSTOM -> "Пользовательская"
                        SortMode.ALPHABETICAL -> "По алфавиту"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                painter = painterResource(
                    Res.drawable.ic_add_circle_24
                ),
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            DropdownMenuItem(
                text = {
                    Text("По алфавиту")
                },

                leadingIcon = {
                    Icon(
                        painter = painterResource(
                            Res.drawable.ic_sort_by_alpha_24
                        ),
                        contentDescription = null
                    )
                },

                trailingIcon = {
                    if (sortMode == SortMode.ALPHABETICAL) {
                        Icon(
                            painter = painterResource(
                                Res.drawable.ic_check_24
                            ),
                            contentDescription = null
                        )
                    }
                },

                onClick = {
                    expanded = false
                    onSortSelected(SortMode.ALPHABETICAL)
                }
            )

            DropdownMenuItem(
                text = {
                    Text("Пользовательская")
                },

                leadingIcon = {
                    Icon(
                        painter = painterResource(
                            Res.drawable.ic_drag_pan_24
                        ),
                        contentDescription = null
                    )
                },

                trailingIcon = {
                    if (sortMode == SortMode.CUSTOM) {
                        Icon(
                            painter = painterResource(
                                Res.drawable.ic_check_24
                            ),
                            contentDescription = null
                        )
                    }
                },

                onClick = {
                    expanded = false
                    onSortSelected(SortMode.CUSTOM)
                }
            )
        }
    }
}