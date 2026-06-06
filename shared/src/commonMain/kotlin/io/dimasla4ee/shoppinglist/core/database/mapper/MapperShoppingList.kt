package io.dimasla4ee.shoppinglist.core.database.mapper

import io.dimasla4ee.shoppinglist.core.database.entity.ShoppingListEntity
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList

fun ShoppingListEntity.toDomain(): ShoppingList {
    return ShoppingList(
        id = id,
        name = name,
        icon = icon,
        products = emptyList()
    )
}

fun ShoppingList.toEntity(): ShoppingListEntity {
    return ShoppingListEntity(
        id = id,
        name = name,
        icon = icon
    )
}