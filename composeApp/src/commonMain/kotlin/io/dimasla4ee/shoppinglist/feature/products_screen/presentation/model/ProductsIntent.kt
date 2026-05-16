package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import io.dimasla4ee.shoppinglist.feature.products_screen.domain.model.UnitType

sealed interface ProductsIntent {

    data class ChangeName(val name: String) : ProductsIntent
    data class ChangeCount(val count: String) : ProductsIntent
    data class ChangeUnit(val unit: UnitType) : ProductsIntent

    data object IncreaseCount : ProductsIntent
    data object DecreaseCount : ProductsIntent
    data object AddItem : ProductsIntent

    data object ToggleBottomSheet : ProductsIntent
    data class ToggleItemChecked(val id: Long) : ProductsIntent
}