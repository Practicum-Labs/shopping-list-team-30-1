package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                    count = intent.count
                )
            }

            is ProductsIntent.ChangeUnit -> {
                _state.value = _state.value.copy(
                    unit = intent.unit
                )
            }

            ProductsIntent.IncreaseCount -> {

                val currentCount = _state.value.count.toIntOrNull() ?: 0

                _state.value = _state.value.copy(
                    count = (currentCount + 1).toString()
                )
            }

            ProductsIntent.DecreaseCount -> {

                val currentCount = _state.value.count.toIntOrNull() ?: return

                if (currentCount > 0) {
                    _state.value = _state.value.copy(
                        count = (currentCount - 1).toString()
                    )
                }
            }
            ProductsIntent.ToggleBottomSheet -> {

                _state.value = _state.value.copy(
                    isBottomSheetOpen = !_state.value.isBottomSheetOpen
                )
            }
        }
    }
}