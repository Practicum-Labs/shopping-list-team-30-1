package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.components.topbar.AppTopBar
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.AddProductUiState
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsIntent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import sh.calvin.reorderable.rememberReorderableLazyListState
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.content_back
import shoppinglist.composeapp.generated.resources.content_menu
import shoppinglist.composeapp.generated.resources.ic_add_56
import shoppinglist.composeapp.generated.resources.ic_arrow_back_24
import shoppinglist.composeapp.generated.resources.ic_drag_pan_24
import shoppinglist.composeapp.generated.resources.ic_fab_check_56
import shoppinglist.composeapp.generated.resources.ic_menu_24
import shoppinglist.composeapp.generated.resources.ic_sort_by_alpha_24

private const val BOTTOM_SHEET_HEIGHT_FRACTION = 0.5f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    listName: String,
    state: AddProductUiState,
    onMenuClick: () -> Unit,
    onIntent: (ProductsIntent) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null
) {

    var sheetHeight by remember { mutableIntStateOf(0) }

    val density = LocalDensity.current

    val fabBottomPadding by animateDpAsState(
        targetValue = if (state.isBottomSheetOpen && sheetHeight > 0) {
            with(density) { sheetHeight.toDp() } + 16.dp
        } else {
            16.dp
        }
    )

    val hapticFeedback = LocalHapticFeedback.current
    val lazyListState = rememberLazyListState()
    val reorderableLazyListState = rememberReorderableLazyListState(lazyListState) { from, to ->
        onIntent(
            ProductsIntent.ReorderProduct(
                fromIndex = from.index,
                toIndex = to.index
            )
        )
        hapticFeedback.performHapticFeedback(HapticFeedbackType.SegmentFrequentTick)
    }
    val isCustomSort = state.sortMode == SortMode.CUSTOM

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Основной контент
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AppTopBar(
                title = listName,
                navigationIcon = {
                    if (onBackClick != null) {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(
                                painter = painterResource(
                                    Res.drawable.ic_arrow_back_24
                                ),
                                contentDescription = stringResource(
                                    Res.string.content_back
                                )
                            )
                        }
                    }
                },

                actions = listOf(
                    ActionItem(
                        iconRes = Res.drawable.ic_menu_24,
                        label = stringResource(
                            Res.string.content_menu
                        ),
                        onClick = onMenuClick
                    )
                )
            )

            SortModeIndicator(
                sortMode = state.sortMode,
                onToggle = { onIntent(ProductsIntent.ToggleSortMode) }
            )

            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                val isPlaceholderVisible = state.items.isEmpty() && !state.isBottomSheetOpen

                when (isPlaceholderVisible) {
                    true -> ItemListPlaceholder(Modifier.fillMaxSize())
                    false -> LazyColumn(
                        state = lazyListState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = state.sortedItems,
                            key = { item -> item.id }
                        ) { item ->
                            ReorderableShoppingItem(
                                item = item,
                                state = reorderableLazyListState,
                                hapticFeedback = hapticFeedback,
                                onCheckedChange = { onIntent(ProductsIntent.ToggleItemChecked(item)) },
                                showDragHandle = isCustomSort
                            )
                        }
                    }
                }
            }
        }

        // Затемнение
        AnimatedVisibility(
            visible = state.isBottomSheetOpen,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.scrim.copy(
                            alpha = 0.32f
                        )
                    )
                    .clickable {
                        onIntent(
                            ProductsIntent.ToggleBottomSheet
                        )
                    }
            )
        }

        // Bottom Sheet
        AnimatedVisibility(
            visible = state.isBottomSheetOpen,
            modifier = Modifier.align(Alignment.BottomCenter),

            enter = slideInVertically(
                initialOffsetY = { it }
            ),

            exit = slideOutVertically(
                targetOffsetY = { it }
            )
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(BOTTOM_SHEET_HEIGHT_FRACTION)
                    .padding(horizontal = 6.dp)
                    .imePadding()
                    .navigationBarsPadding()
                    .onGloballyPositioned { coordinates ->
                        sheetHeight = coordinates.size.height
                    },

                shape = RoundedCornerShape(
                    topStart = 28.dp,
                    topEnd = 28.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),

                color = MaterialTheme.colorScheme.primaryContainer
            ) {

                AddProductBottomSheet(
                    name = state.name,
                    amount = state.amount,
                    unit = state.unit,

                    onNameChange = {
                        onIntent(
                            ProductsIntent.ChangeName(it)
                        )
                    },

                    onCountChange = {
                        onIntent(
                            ProductsIntent.ChangeCount(it)
                        )
                    },

                    onUnitChange = {
                        onIntent(
                            ProductsIntent.ChangeUnit(it)
                        )
                    },

                    onIncreaseClick = {
                        onIntent(
                            ProductsIntent.IncreaseCount
                        )
                    },

                    onDecreaseClick = {
                        onIntent(
                            ProductsIntent.DecreaseCount
                        )
                    }
                )
            }
        }

        // FAB
        FloatingActionButton(
            onClick = {
                if (state.isBottomSheetOpen) {
                    onIntent(ProductsIntent.AddItem)
                } else {
                    onIntent(ProductsIntent.ToggleBottomSheet)
                }
            },

            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 16.dp,
                    bottom = fabBottomPadding
                )
                .navigationBarsPadding(),

            containerColor = MaterialTheme.colorScheme.primary
        ) {

            Icon(
                painter = painterResource(
                    if (state.isBottomSheetOpen) {
                        Res.drawable.ic_fab_check_56
                    } else {
                        Res.drawable.ic_add_56
                    }
                ),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Deprecated("Временная заглушка. Заменить на меню")
@Composable
fun SortModeIndicator(
    sortMode: SortMode,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(
                when (sortMode) {
                    SortMode.CUSTOM -> Res.drawable.ic_drag_pan_24
                    SortMode.ALPHABETICAL -> Res.drawable.ic_sort_by_alpha_24
                }
            ),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = when (sortMode) {
                SortMode.CUSTOM -> "Sort custom"
                SortMode.ALPHABETICAL -> "Sort alphabet"
            },
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview
@PreviewLightDark
@Composable
private fun AddItemScreenPreview() {
    ShoppingListTheme {
        AddItemRoute(
            listId = 0,
            listName = "Bobs",
            onBackClick = {},
            onMenuClick = {}
        )
    }
}