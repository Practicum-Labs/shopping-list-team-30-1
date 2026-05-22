package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import androidx.lifecycle.viewModelScope
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.GetSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.SetSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.ProductInteractor
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.state.ProductDialog
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productInteractor: ProductInteractor,
    private val getSortModeUseCase: GetSortModeUseCase,
    private val setSortModeUseCase: SetSortModeUseCase
) : MviViewModel<ProductsIntent, ProductsState, ProductsEffect>(
    initialState = ProductsState()
) {
    private var listId: Long = 0

    fun init(listId: Long) {
        this.listId = listId

        observeProducts()
        observeSortMode()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            productInteractor
                .getProductsOfList(listId)
                .collect { products ->
                    updateState { it.copy(items = products) }
                }
        }
    }

    private fun observeSortMode() {
        viewModelScope.launch {
            getSortModeUseCase(listId)
                .collect { mode ->
                    updateState { it.copy(sortMode = mode) }
                }
        }
    }

    private fun String.toIntOrZero(): Int = toIntOrNull() ?: 0

    override fun reduce(
        intent: ProductsIntent,
        current: ProductsState
    ): ProductsState = when (intent) {
        is ProductsIntent.ChangeName -> current.copy(name = intent.name)
        is ProductsIntent.ChangeCount -> current.copy(amount = intent.amount)
        is ProductsIntent.ChangeUnit -> current.copy(unit = intent.unit)
        is ProductsIntent.IncreaseCount ->
            current.copy(amount = (current.amount.toIntOrZero() + 1).toString())

        ProductsIntent.DecreaseCount -> {
            val amount = current.amount.toIntOrZero()
            current.copy(amount = (amount - 1).coerceAtLeast(0).toString())
        }

        ProductsIntent.ToggleBottomSheet ->
            current.copy(isBottomSheetOpen = !current.isBottomSheetOpen)

        ProductsIntent.ShowDeleteAllDialog ->
            current.copy(dialog = ProductDialog.DeleteAll)

        ProductsIntent.ShowDeleteCheckedDialog ->
            current.copy(dialog = ProductDialog.DeleteCheckedProducts)

        ProductsIntent.DismissDialog ->
            current.copy(dialog = ProductDialog.None)

        is ProductsIntent.ReorderProduct -> {
            if (current.sortMode != SortMode.CUSTOM) return current

            val items = current.displayedItems.toMutableList().apply {
                val moved = removeAt(intent.fromIndex)
                add(intent.toIndex, moved)
            }.mapIndexed { index, product -> product.copy(position = index) }

            current.copy(items = items)
        }

        ProductsIntent.ToggleMenuBottomSheet ->
            current.copy(isMenuBottomSheetOpen = !current.isMenuBottomSheetOpen)

        is ProductsIntent.EditProduct -> current.copy(
            id = intent.product.id,
            isBottomSheetOpen = !current.isBottomSheetOpen,
            name = intent.product.name,
            amount = intent.product.amount.toString(),
            unit = intent.product.unit
        )

        is ProductsIntent.DeleteProduct,
        ProductsIntent.CommitReorder,
        ProductsIntent.ToggleSortMode,
        is ProductsIntent.ChangeSortMode,
        ProductsIntent.DeleteCheckedProducts,
        ProductsIntent.DeleteAllProducts,
        ProductsIntent.AddItem,
        is ProductsIntent.ToggleItemChecked -> current
    }

    private fun clearState() {
        updateState {
            it.copy(
                id = null,
                name = "",
                amount = "",
                unit = null,
                position = null,
                isBottomSheetOpen = false
            )
        }
    }

    override suspend fun handleIntent(intent: ProductsIntent) {
        when (intent) {
            ProductsIntent.AddItem -> handleAddItem()
            ProductsIntent.DeleteAllProducts -> handleDeleteAll()
            is ProductsIntent.ChangeSortMode -> setSortModeUseCase(listId, intent.mode)
            ProductsIntent.DeleteCheckedProducts -> handleDeleteChecked()
            is ProductsIntent.ToggleItemChecked -> handleToggleChecked(intent.product)
            ProductsIntent.ToggleSortMode -> handleToggleSortMode()
            ProductsIntent.CommitReorder -> handleCommitReorder()
            is ProductsIntent.DeleteProduct -> handleDeleteProduct()

            is ProductsIntent.ReorderProduct,
            is ProductsIntent.ChangeCount,
            is ProductsIntent.ChangeName,
            is ProductsIntent.ChangeUnit,
            ProductsIntent.DecreaseCount,
            ProductsIntent.DismissDialog,
            is ProductsIntent.EditProduct,
            ProductsIntent.IncreaseCount,
            ProductsIntent.ShowDeleteAllDialog,
            ProductsIntent.ShowDeleteCheckedDialog,
            ProductsIntent.ToggleBottomSheet,
            ProductsIntent.ToggleMenuBottomSheet -> Unit
        }
    }

    private suspend fun handleDeleteProduct() {
        state.value.items.find { it.id == state.value.id }?.let { product ->
            productInteractor.deleteProduct(product)
        }
        clearState()
    }

    private suspend fun handleAddItem() {
        val currentState = state.value

        if (currentState.name.isBlank()) return
        productInteractor.addProduct(
            Product(
                id = currentState.id ?: System.currentTimeMillis(),
                listId = listId,
                name = currentState.name.trim(),
                amount = currentState.amount.toFloatOrNull() ?: 0f,
                unit = currentState.unit,
                isChecked = false,
                position = currentState.position ?: currentState.items.size
            )
        )

        clearState()
    }

    private suspend fun handleToggleChecked(
        product: Product
    ) {
        productInteractor.addProduct(
            product.copy(
                isChecked = !product.isChecked
            )
        )
    }

    private suspend fun handleToggleSortMode() {
        val currentMode = state.value.sortMode
        val newMode = when (currentMode) {
            SortMode.CUSTOM -> SortMode.ALPHABETICAL
            SortMode.ALPHABETICAL -> SortMode.CUSTOM
        }

        setSortModeUseCase(
            listId = listId,
            mode = newMode
        )
    }

    private suspend fun handleDeleteAll() {
        productInteractor.deleteAllProducts(listId)

        updateState {
            it.copy(
                dialog = ProductDialog.None,
                isMenuBottomSheetOpen = false
            )
        }
    }

    private suspend fun handleDeleteChecked() {
        productInteractor.deleteCheckedProducts(listId)

        updateState {
            it.copy(
                dialog = ProductDialog.None,
                isMenuBottomSheetOpen = false
            )
        }
    }

    private suspend fun handleCommitReorder() {
        val items = state.value.items
        productInteractor.updateProducts(items)
    }
}
