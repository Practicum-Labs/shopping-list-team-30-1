package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import io.dimasla4ee.shoppinglist.core.mvi.MviEffect

sealed interface ProductsEffect : MviEffect {
    data object NavigateBack : ProductsEffect
}