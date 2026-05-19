package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsViewModel
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddItemRoute(
    listName: String,
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: ProductsViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    AddItemScreen(
        listName = listName,
        state = state,
        onIntent = {
            viewModel.dispatch(it)
        },
        onMenuClick = onMenuClick,
        onBackClick = onBackClick
    )
}