package io.dimasla4ee.shoppinglist.core.domain.model

data class ShoppingList(
    val id: Long,
    val name: String,
    val icon: ShoppingListIcon,
    //val products: List<ShoppingList>,
    val products: List<Product>
)