package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsEffect
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddItemRoute(
    listName: String,
    listId: Long,
    onBackClick: () -> Unit,
    viewModel: ProductsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(listId) {
        viewModel.init(listId, listName)
    }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when(effect) {
                ProductsEffect.NavigateBack -> onBackClick()
            }
        }
    }

    AddItemScreen(
        listName = state.listName.ifBlank { listName },
        state = state,
        onIntent = { viewModel.dispatch(it) },
        modifier = Modifier.fillMaxSize()
    )
}