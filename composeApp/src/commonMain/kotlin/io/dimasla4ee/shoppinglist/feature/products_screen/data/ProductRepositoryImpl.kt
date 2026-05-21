package io.dimasla4ee.shoppinglist.feature.products_screen.data

import io.dimasla4ee.shoppinglist.core.database.dao.ShoppingListDao
import io.dimasla4ee.shoppinglist.core.database.mapper.toDomain
import io.dimasla4ee.shoppinglist.core.database.mapper.toEntity
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val dao: ShoppingListDao
) : ProductRepository {

    override fun getProductsOfList(
        listId: Long
    ): Flow<List<Product>> {

        return dao.getProductsOfList(listId)
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override suspend fun addProduct(product: Product) {
        dao.addProduct(product.toEntity())
    }

    override suspend fun updateProduct(product: Product) {
        dao.updateProduct(product.toEntity())
    }

    override suspend fun deleteProduct(product: Product) {
        dao.deleteProduct(product.toEntity())
    }

    override suspend fun deleteAllProducts(listId: Long) {
        dao.deleteAllProductsOfList(listId)
    }

    override suspend fun deleteCheckedProducts(listId: Long) {
        dao.deleteCheckedProducts(listId)
    }
}