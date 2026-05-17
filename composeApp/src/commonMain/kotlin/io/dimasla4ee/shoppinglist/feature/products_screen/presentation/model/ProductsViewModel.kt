package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.AddProductUiState
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsIntent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val _state = MutableStateFlow(AddProductUiState())
    val state = _state.asStateFlow()

    fun onIntent(intent: ProductsIntent) {

        when (intent) {

            is ProductsIntent.ChangeName -> {
                _state.value = _state.value.copy(
                    name = intent.name
                )
            }

            is ProductsIntent.ChangeCount -> {
                _state.value = _state.value.copy(
                    amount = intent.amount
                )
            }

            is ProductsIntent.ChangeUnit -> {
                _state.value = _state.value.copy(
                    unit = intent.unit
                )
            }

            ProductsIntent.IncreaseCount -> {

                val currentCount = _state.value.amount.toIntOrNull() ?: 0

                _state.value = _state.value.copy(
                    amount = (currentCount + 1).toString()
                )
            }

            ProductsIntent.DecreaseCount -> {

                val currentCount = _state.value.amount.toIntOrNull() ?: return

                if (currentCount > 0) {
                    _state.value = _state.value.copy(
                        amount = (currentCount - 1).toString()
                    )
                }
            }

            ProductsIntent.ToggleBottomSheet -> {

                _state.value = _state.value.copy(
                    isBottomSheetOpen = !_state.value.isBottomSheetOpen
                )
            }

            ProductsIntent.AddItem -> {

                val currentState = _state.value

                if (currentState.name.isBlank()) return

                val newItem = Product(
                    id = System.currentTimeMillis(),
                    name = currentState.name,
                    amount = currentState.amount,
                    unit = currentState.unit
                )

                _state.value = currentState.copy(
                    items = currentState.items + newItem,

                    name = "",
                    amount = "",
                    unit = MeasurementUnit.PIECE,

                    isBottomSheetOpen = false
                )
            }

            is ProductsIntent.ToggleItemChecked -> {

                _state.value = _state.value.copy(
                    items = _state.value.items.map { item ->

                        if (item.id == intent.id) {

                            item.copy(isChecked = !item.isChecked)

                        } else {
                            item
                        }
                    }
                )
            }
        }
    }
}