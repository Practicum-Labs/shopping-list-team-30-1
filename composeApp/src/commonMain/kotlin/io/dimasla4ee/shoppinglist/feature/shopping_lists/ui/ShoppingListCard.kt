package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListCardEvent
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListItem
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.cd_change_shopping_list_icon
import shoppinglist.composeapp.generated.resources.cd_copy_shopping_list
import shoppinglist.composeapp.generated.resources.cd_delete_shopping_list
import shoppinglist.composeapp.generated.resources.cd_edit_shopping_list
import shoppinglist.composeapp.generated.resources.ic_copy_24
import shoppinglist.composeapp.generated.resources.ic_delete_24
import shoppinglist.composeapp.generated.resources.ic_docs_add_24
import shoppinglist.composeapp.generated.resources.ic_edit_24
import shoppinglist.composeapp.generated.resources.ic_shopping_cart_24

@Composable
fun ShoppingListCard(
    listItem: ShoppingListItem,
    onEvent: (ShoppingListCardEvent) -> Unit,
    modifier: Modifier = Modifier,
    colors: CardColors = ShoppingListCardDefaults.shoppingListCardColors(),
    elevation: CardElevation = ShoppingListCardDefaults.shoppingListCardElevation()
) {
    val scope = rememberCoroutineScope()
    val swipeState = rememberSwipeToDismissBoxState()
    val isStartToEnd = swipeState.dismissDirection == SwipeToDismissBoxValue.StartToEnd
    val backgroundColor = when (isStartToEnd) {
        true -> Color.Transparent
        false -> MaterialTheme.colorScheme.errorContainer
    }
    val horizontalArrangement = when (isStartToEnd) {
        true -> Arrangement.spacedBy(8.dp)
        false -> Arrangement.End
    }
    val closeMenuAndFireEvent: (ShoppingListCardEvent) -> Unit = { event ->
        onEvent(event)
        scope.launch { swipeState.reset() }
    }

    SwipeToDismissBox(
        state = swipeState,
        onDismiss = { state ->
            if (state == SwipeToDismissBoxValue.EndToStart) {
                onEvent(ShoppingListCardEvent.Delete(listItem))
            }
        },
        backgroundContent = {
            Surface(
                modifier = modifier.wrapContentSize(),
                shape = RoundedCornerShape(12.dp),
                color = backgroundColor
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = horizontalArrangement
                ) {
                    when (swipeState.dismissDirection) {
                        SwipeToDismissBoxValue.StartToEnd -> Actions(
                            onEdit = { closeMenuAndFireEvent(ShoppingListCardEvent.Edit(listItem)) },
                            onCopy = { closeMenuAndFireEvent(ShoppingListCardEvent.Copy(listItem)) },
                            onIconChange = {
                                closeMenuAndFireEvent(ShoppingListCardEvent.ChangeIcon(listItem))
                            }
                        )

                        SwipeToDismissBoxValue.EndToStart -> {
                            Icon(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                painter = painterResource(Res.drawable.ic_delete_24),
                                contentDescription = stringResource(Res.string.cd_delete_shopping_list),
                                tint = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }

                        SwipeToDismissBoxValue.Settled -> {}
                    }
                }
            }
        }
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            colors = colors,
            elevation = elevation,
            onClick = { onEvent(ShoppingListCardEvent.Click(listItem)) }
        ) {
            ItemContent(
                listItem,
                onIconClick = {
                    onEvent(
                        ShoppingListCardEvent.ChangeIcon(listItem)
                    )
                }
            )
        }
    }
}

@Composable
private fun Actions(
    onEdit: () -> Unit,
    onCopy: () -> Unit,
    onIconChange: () -> Unit
) {
    AppIconButton(
        iconRes = Res.drawable.ic_edit_24,
        contentDescription = stringResource(Res.string.cd_edit_shopping_list)
    ) { onEdit() }
    AppIconButton(
        iconRes = Res.drawable.ic_copy_24,
        contentDescription = stringResource(Res.string.cd_copy_shopping_list)
    ) { onCopy() }
    AppIconButton(
        iconRes = Res.drawable.ic_docs_add_24,
        contentDescription = stringResource(Res.string.cd_change_shopping_list_icon)
    ) { onIconChange() }
}

@Composable
private fun ItemContent(
    listItem: ShoppingListItem,
    onIconClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconButton(
            iconRes = listItem.iconRes,
            contentDescription = null,
            onClick = onIconClick,
        )
        Text(listItem.name)
    }
}

@Preview
@PreviewLightDark
@Composable
private fun ShoppingListCardPreview(
    @PreviewParameter(ShoppingListItemProvider::class)
    listItem: ShoppingListItem
) {
    ShoppingListTheme {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            ShoppingListCard(listItem, {})
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun ShoppingListCardListPreview() {
    var items by remember {
        mutableStateOf(
            listOf(
                ShoppingListItem(1L, "Продукты", Res.drawable.ic_shopping_cart_24),
                ShoppingListItem(2L, "Аптека", Res.drawable.ic_shopping_cart_24),
                ShoppingListItem(3L, "Хозтовары", Res.drawable.ic_shopping_cart_24),
                ShoppingListItem(4L, "Игрушки", Res.drawable.ic_shopping_cart_24),
                ShoppingListItem(5L, "Мыльнорыльные", Res.drawable.ic_shopping_cart_24),
                ShoppingListItem(6L, "Для учёбы", Res.drawable.ic_shopping_cart_24),
                ShoppingListItem(7L, "Подарки", Res.drawable.ic_shopping_cart_24)
            )
        )
    }

    ShoppingListTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = items,
                    key = { it.id }
                ) { item ->
                    ShoppingListCard(
                        listItem = item,
                        onEvent = { event ->
                            when (event) {
                                is ShoppingListCardEvent.Copy -> {
                                    val newId = items.maxOfOrNull { it.id }?.let { it + 1 } ?: 1L
                                    val newItem = item.copy(
                                        id = newId,
                                        name = "${item.name} (копия)"
                                    )
                                    val index = items.indexOf(item)
                                    items = items.toMutableList().apply {
                                        add(index + 1, newItem)
                                    }
                                }

                                is ShoppingListCardEvent.Delete -> {
                                    items = items.filter { it.id != item.id }
                                }

                                else -> {}
                            }
                        }
                    )
                }
            }
        }
    }
}