package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.components.AppTopBar
import io.dimasla4ee.shoppinglist.core.presentation.components.TopBarIcon
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsIntent
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.add_items_hint
import shoppinglist.composeapp.generated.resources.content_back
import shoppinglist.composeapp.generated.resources.content_menu
import shoppinglist.composeapp.generated.resources.empty_list_message
import shoppinglist.composeapp.generated.resources.ic_add_56
import shoppinglist.composeapp.generated.resources.ic_arrow_back_24
import shoppinglist.composeapp.generated.resources.ic_fab_check_56
import shoppinglist.composeapp.generated.resources.ic_menu_24
import shoppinglist.composeapp.generated.resources.img_product_list

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    viewModel: ProductsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    // VM → Sheet
    LaunchedEffect(state.isBottomSheetOpen) {
        scope.launch {
            if (state.isBottomSheetOpen) {
                sheetState.show()
            } else {
                sheetState.hide()
            }
        }
    }

    // Sheet → VM
    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.currentValue }
            .distinctUntilChanged()
            .collectLatest { value ->
                val isOpen = value == SheetValue.Expanded
                if (state.isBottomSheetOpen != isOpen) {
                    viewModel.onIntent(ProductsIntent.ToggleBottomSheet)
                }
            }
    }

    Box(modifier = modifier.fillMaxSize()) {

        // Основной контент экрана
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTopBar(
                title = "Название списка",
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
                    TopBarIcon(
                        icon = Res.drawable.ic_menu_24,
                        contentDescription = stringResource(Res.string.content_menu),
                        onClick = onMenuClick
                    )
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(Res.drawable.img_product_list),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                modifier = Modifier.padding(horizontal = 44.dp),
                text = stringResource(Res.string.empty_list_message),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 44.dp),
                textAlign = TextAlign.Center,
                text = stringResource(Res.string.add_items_hint),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        // Modal Bottom Sheet
        if (state.isBottomSheetOpen || sheetState.currentValue != SheetValue.Hidden) {
            ModalBottomSheet(
                onDismissRequest = {
                    viewModel.onIntent(ProductsIntent.ToggleBottomSheet)
                },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(16.dp),
                    bottomEnd = CornerSize(16.dp)
                ),
                modifier = Modifier.padding(horizontal = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp)
                ) {
                    AddProductBottomSheet(
                        name = state.name,
                        count = state.count,
                        unit = state.unit,
                        onNameChange = { viewModel.onIntent(ProductsIntent.ChangeName(it)) },
                        onCountChange = { viewModel.onIntent(ProductsIntent.ChangeCount(it)) },
                        onUnitChange = { viewModel.onIntent(ProductsIntent.ChangeUnit(it)) },
                        onIncreaseClick = { viewModel.onIntent(ProductsIntent.IncreaseCount) },
                        onDecreaseClick = { viewModel.onIntent(ProductsIntent.DecreaseCount) }
                    )
                }
            }
        }

        // FAB
        FloatingActionButton(
            onClick = {
                viewModel.onIntent(ProductsIntent.ToggleBottomSheet)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .offset {
                    // Безопасная проверка
                    if (state.isBottomSheetOpen &&
                        sheetState.currentValue == SheetValue.Expanded) {
                        try {
                            IntOffset(x = 0, y = sheetState.requireOffset().toInt() - 2400)
                        } catch (e: IllegalStateException) {
                            IntOffset.Zero
                        }
                    } else {
                        IntOffset.Zero
                    }
                },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                painter = painterResource(
                    if (state.isBottomSheetOpen) Res.drawable.ic_fab_check_56
                    else Res.drawable.ic_add_56
                ),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
private fun ProductsScreenPreview() {
    ShoppingListTheme {
        ProductsScreen(
            modifier = Modifier.fillMaxSize(),
            onBackClick = {},
            onMenuClick = {}
        )
    }
}