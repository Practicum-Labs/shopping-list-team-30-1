package io.dimasla4ee.shoppinglist.core.database.mapper

import io.dimasla4ee.shoppinglist.core.database.entity.ProductEntity
import io.dimasla4ee.shoppinglist.core.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        listId = listId,
        name = name,
        amount = amount,
        unit = unit,
        isChecked = isChecked,
        position = position
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        listId = listId,
        name = name,
        amount = amount,
        unit = unit,
        isChecked = isChecked,
        position = position
    )
}