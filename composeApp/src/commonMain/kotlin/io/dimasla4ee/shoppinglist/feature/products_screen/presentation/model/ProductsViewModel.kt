package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import androidx.lifecycle.viewModelScope
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.GetSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.SetSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import kotlinx.coroutines.launch

class ProductsViewModel(
    val getSortModeUseCase: GetSortModeUseCase,
    val setSortModeUseCase: SetSortModeUseCase
) : MviViewModel<ProductsIntent, AddProductUiState, ProductsEffect>(
    initialState = AddProductUiState()
) {

    private var listId: Long = 0

    fun getSortMode(listId: Long) {
        this.listId = listId
        viewModelScope.launch {
            getSortModeUseCase(listId).collect { mode ->
                updateState { it.copy(sortMode = mode) }
            }
        }
    }

    override fun reduce(intent: ProductsIntent, current: AddProductUiState): AddProductUiState {
        return when (intent) {

            is ProductsIntent.ChangeName ->
                current.copy(name = intent.name)

            is ProductsIntent.ChangeCount ->
                current.copy(amount = intent.amount)

            is ProductsIntent.ChangeUnit ->
                current.copy(unit = intent.unit)

            is ProductsIntent.IncreaseCount -> {
                val count = current.amount.toIntOrNull() ?: 0
                current.copy(amount = (count + 1).toString())
            }

            ProductsIntent.DecreaseCount -> {
                val count = current.amount.toIntOrNull() ?: 0
                current.copy(amount = (count - 1).coerceAtLeast(0).toString())
            }

            ProductsIntent.ToggleBottomSheet ->
                current.copy(
                    isBottomSheetOpen = !current.isBottomSheetOpen
                )

            is ProductsIntent.ReorderProduct -> {
                if (current.sortMode != SortMode.CUSTOM) return current

                val items = current.items.toMutableList()
                items.removeAt(intent.fromIndex).let { movedItem ->
                    items.add(intent.toIndex, movedItem)
                }

                val reordered = items.mapIndexed { index, product ->
                    product.copy(position = index)
                }

                current.copy(items = reordered)
            }

            ProductsIntent.ToggleSortMode,
            ProductsIntent.AddItem,
            is ProductsIntent.ToggleItemChecked -> current
        }

    }

    override suspend fun handleIntent(intent: ProductsIntent) {
        when (intent) {

            ProductsIntent.AddItem -> {
                val currentState = state.value

                if (currentState.name.isBlank()) return

                val newItem = Product(
                    id = System.currentTimeMillis(),
                    name = currentState.name,
                    amount = currentState.amount,
                    unit = currentState.unit,
                    position = currentState.items.size
                )

                updateState {
                    it.copy(
                        items = it.items + newItem,
                        name = "",
                        amount = "",
                        unit = MeasurementUnit.PIECE,
                        isBottomSheetOpen = false
                    )
                }
            }

            is ProductsIntent.ToggleItemChecked -> {
                val products = state.value.items.toMutableList().apply {
                    remove(intent.product)
                    add(intent.product.copy(isChecked = !intent.product.isChecked))
                }

                updateState { it.copy(items = products) }
            }

            ProductsIntent.ToggleSortMode -> {
                val currentMode = state.value.sortMode
                val newMode = when (currentMode) {
                    SortMode.CUSTOM -> SortMode.ALPHABETICAL
                    SortMode.ALPHABETICAL -> SortMode.CUSTOM
                }
                setSortModeUseCase(listId, newMode)
            }

            else -> Unit
        }
    }
}
