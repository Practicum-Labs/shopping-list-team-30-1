package io.dimasla4ee.shoppinglist.feature.products_screen.domain

import io.dimasla4ee.shoppinglist.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProductsOfList(listId: Long): Flow<List<Product>>

    suspend fun addProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun deleteAllProducts(listId: Long)

    suspend fun deleteCheckedProducts(listId: Long)
}