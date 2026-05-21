package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.state

sealed class ProductDialog {

    data object None : ProductDialog()

    data object DeleteAll : ProductDialog()

    data object DeleteCheckedProducts : ProductDialog()
}