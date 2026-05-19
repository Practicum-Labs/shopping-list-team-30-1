package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel

class ProductsViewModel :
    MviViewModel<ProductsIntent, AddProductUiState, ProductsEffect>(
        AddProductUiState()
    ) {

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
                val items = current.items.toMutableList()

                if (
                    intent.fromIndex !in items.indices ||
                    intent.toIndex !in items.indices
                ) {
                    return current
                }

                val movedItem = items.removeAt(intent.fromIndex)
                items.add(intent.toIndex, movedItem)

                val reordered = items.mapIndexed { index, product ->
                    product.copy(position = index)
                }

                current.copy(items = reordered)
            }


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

                updateState { current ->
                    current.copy(
                        items = current.items.map { item ->
                            if (item.id == intent.id) {
                                item.copy(
                                    isChecked = !item.isChecked
                                )
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            ProductsIntent.ToggleBottomSheet -> Unit
            else -> Unit
        }
    }
}
