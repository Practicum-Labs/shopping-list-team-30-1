package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.components.topbar.AppTopBar
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsState
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsIntent
import io.dimasla4ee.shoppinglist.feature.products_screen.ui.bottom_sheets.AddProductBottomSheet
import io.dimasla4ee.shoppinglist.feature.products_screen.ui.menu.ProductsMenuBottomSheet
import io.dimasla4ee.shoppinglist.feature.products_screen.ui.menu.SortSelector
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import sh.calvin.reorderable.rememberReorderableLazyListState
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.content_back
import shoppinglist.composeapp.generated.resources.content_menu
import shoppinglist.composeapp.generated.resources.ic_add_56
import shoppinglist.composeapp.generated.resources.ic_arrow_back_24
import shoppinglist.composeapp.generated.resources.ic_drag_pan_24
import shoppinglist.composeapp.generated.resources.ic_menu_24
import shoppinglist.composeapp.generated.resources.ic_sort_by_alpha_24

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    listName: String,
    state: ProductsState,
    onMenuClick: () -> Unit,
    onIntent: (ProductsIntent) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null
) {
    val sheetState = rememberModalBottomSheetState()

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

        ProductsMenuBottomSheet(
            visible = state.isMenuBottomSheetOpen,
            sortMode = state.sortMode,
            onDismiss = {
                onIntent(ProductsIntent.ToggleMenuBottomSheet)
            },
            onSortClick = {
                onIntent(ProductsIntent.ToggleSortMode)
            },
            onDeleteAllClick = {
                onIntent(ProductsIntent.DeleteAllProducts)
            },
            onDeleteCheckClick = {
                onIntent(ProductsIntent.DeleteCheckedProducts)
            }
        )
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
                        IconButton(onClick = onBackClick) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_arrow_back_24),
                                contentDescription = stringResource(Res.string.content_back)
                            )
                        }
                    }
                },
                actions = listOf(
                    ActionItem(
                        iconRes = Res.drawable.ic_menu_24,
                        label = stringResource(Res.string.content_menu),
                        onClick = { onIntent(ProductsIntent.ToggleMenuBottomSheet) }
                    )
                )
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

            // Bottom Sheet
            if (state.isBottomSheetOpen) {
                ModalBottomSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = AppDimensions.paddingVerySmall),
                    onDismissRequest = { onIntent(ProductsIntent.ToggleBottomSheet) },
                    sheetState = sheetState,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    dragHandle = null,
                    shape = AppDimensions.BottomSheet.topCornerRadius
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = AppDimensions.paddingMedium),
                        contentAlignment = Alignment.Center
                    ) {
                        Surface(
                            modifier = Modifier.size(AppDimensions.BottomSheet.handlerSize),
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.outlineVariant
                        ) {}
                    }

                    AddProductBottomSheet(
                        name = state.name,
                        amount = state.amount,
                        unit = state.unit,
                        onNameChange = { onIntent(ProductsIntent.ChangeName(it)) },
                        onCountChange = { onIntent(ProductsIntent.ChangeCount(it)) },
                        onUnitChange = { onIntent(ProductsIntent.ChangeUnit(it)) },
                        onIncreaseClick = { onIntent(ProductsIntent.IncreaseCount) },
                        onDecreaseClick = { onIntent(ProductsIntent.DecreaseCount) },
                        onFabClick = { onIntent(ProductsIntent.AddItem) }
                    )
                }
            }

            // FAB
            AppFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = AppDimensions.paddingMedium, bottom = AppDimensions.paddingExtraBig),
                onClick = { onIntent(ProductsIntent.ToggleBottomSheet) },
                iconRes = painterResource(Res.drawable.ic_add_56)
            )

            SortModeIndicator(
                modifier = Modifier.align(Alignment.BottomCenter),
                sortMode = state.sortMode,
                onToggle = { onIntent(ProductsIntent.ToggleSortMode) }
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