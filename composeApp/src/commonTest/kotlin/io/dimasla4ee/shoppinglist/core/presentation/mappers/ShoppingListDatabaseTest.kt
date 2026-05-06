package io.dimasla4ee.shoppinglist.core.presentation.mappers

import androidx.room.Room
import androidx.room.RoomDatabase
import io.dimasla4ee.shoppinglist.app.di.dataModule
import io.dimasla4ee.shoppinglist.core.database.dao.ShoppingListDao
import io.dimasla4ee.shoppinglist.core.database.db.ShoppingListDatabase
import io.dimasla4ee.shoppinglist.core.database.entity.ProductEntity
import io.dimasla4ee.shoppinglist.core.database.entity.ShoppingListEntity
import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

class ShoppingListDatabaseTest : KoinTest {

    private lateinit var database: ShoppingListDatabase
    private lateinit var dao: ShoppingListDao


    private val testPlatformDataModule = module {
        factory<RoomDatabase.Builder<ShoppingListDatabase>> {
            Room.inMemoryDatabaseBuilder<ShoppingListDatabase>()
        }
    }

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            modules(dataModule, testPlatformDataModule)
        }

        database = get()
        dao = database.shoppingListDao()
    }

    // ========================
    // Shopping List CRUD
    // ========================

    @Test
    fun `addShoppingList - inserts and retrieves a single list`() = runTest {
        val list = createShoppingList(name = "Groceries")

        dao.addShoppingList(list)

        val result = dao.getShoppingLists().first()
        assertEquals(1, result.size)
        assertEquals("Groceries", result[0].name)
    }

    @Test
    fun `addShoppingList - inserts multiple lists`() = runTest {
        dao.addShoppingList(createShoppingList(name = "Groceries"))
        dao.addShoppingList(createShoppingList(name = "Hardware"))
        dao.addShoppingList(createShoppingList(name = "Clothes"))

        val result = dao.getShoppingLists().first()
        assertEquals(3, result.size)
    }

    @Test
    fun `addShoppingList - replace on conflict updates existing list`() = runTest {
        dao.addShoppingList(createShoppingList(id = 1, name = "Old Name"))
        dao.addShoppingList(createShoppingList(id = 1, name = "New Name"))

        val result = dao.getShoppingLists().first()
        assertEquals(1, result.size)
        assertEquals("New Name", result[0].name)
    }

    @Test
    fun `deleteShoppingList - removes the list`() = runTest {
        val list = createShoppingList(id = 1, name = "Groceries")
        dao.addShoppingList(list)

        dao.deleteShoppingList(list)

        val result = dao.getShoppingLists().first()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `getShoppingLists - returns empty list when no data`() = runTest {
        val result = dao.getShoppingLists().first()
        assertTrue(result.isEmpty())
    }

    // ========================
    // Product CRUD
    // ========================

    @Test
    fun `addProduct - inserts and retrieves a single product`() = runTest {
        dao.addShoppingList(createShoppingList(id = 1))

        val product = createProduct(listId = 1, name = "Milk")
        dao.addProduct(product)

        val result = dao.getProducts().first()
        assertEquals(1, result.size)
        assertEquals("Milk", result[0].name)
    }

    @Test
    fun `addProduct - inserts multiple products`() = runTest {
        dao.addShoppingList(createShoppingList(id = 1))

        dao.addProduct(createProduct(listId = 1, name = "Milk"))
        dao.addProduct(createProduct(listId = 1, name = "Bread"))
        dao.addProduct(createProduct(listId = 1, name = "Eggs"))

        val result = dao.getProducts().first()
        assertEquals(3, result.size)
    }

    @Test
    fun `addProduct - replace on conflict updates existing product`() = runTest {
        dao.addShoppingList(createShoppingList(id = 1))

        dao.addProduct(createProduct(id = 1, listId = 1, name = "Old Product"))
        dao.addProduct(createProduct(id = 1, listId = 1, name = "Updated Product"))

        val result = dao.getProducts().first()
        assertEquals(1, result.size)
        assertEquals("Updated Product", result[0].name)
    }

    @Test
    fun `deleteProduct - removes the product`() = runTest {
        dao.addShoppingList(createShoppingList(id = 1))

        val product = createProduct(id = 1, listId = 1, name = "Milk")
        dao.addProduct(product)

        dao.deleteProduct(product)

        val result = dao.getProducts().first()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `getProducts - returns empty list when no products`() = runTest {
        val result = dao.getProducts().first()
        assertTrue(result.isEmpty())
    }

    // ========================
    // Products filtered by list
    // ========================

    @Test
    fun `getProductsOfList - returns only products belonging to specified list`() = runTest {
        dao.addShoppingList(createShoppingList(id = 1, name = "Groceries"))
        dao.addShoppingList(createShoppingList(id = 2, name = "Hardware"))

        dao.addProduct(createProduct(listId = 1, name = "Milk"))
        dao.addProduct(createProduct(listId = 1, name = "Bread"))
        dao.addProduct(createProduct(listId = 2, name = "Hammer"))

        val groceryProducts = dao.getProductsOfList(1).first()
        val hardwareProducts = dao.getProductsOfList(2).first()

        assertEquals(2, groceryProducts.size)
        assertTrue(groceryProducts.all { it.listId == 1L })

        assertEquals(1, hardwareProducts.size)
        assertEquals("Hammer", hardwareProducts[0].name)
    }

    @Test
    fun `getProductsOfList - returns empty list for list with no products`() = runTest {
        dao.addShoppingList(createShoppingList(id = 1))

        val result = dao.getProductsOfList(1).first()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `getProductsOfList - returns empty list for non-existent list`() = runTest {
        val result = dao.getProductsOfList(999).first()
        assertTrue(result.isEmpty())
    }

    // ========================
    // Cascade delete
    // ========================

    @Test
    fun `deleteShoppingList - cascades deletion to all products of that list`() = runTest {
        val list = createShoppingList(id = 1, name = "Groceries")
        dao.addShoppingList(list)

        dao.addProduct(createProduct(listId = 1, name = "Milk"))
        dao.addProduct(createProduct(listId = 1, name = "Bread"))
        dao.addProduct(createProduct(listId = 1, name = "Eggs"))

        // Убеждаемся, что продукты существуют
        assertEquals(3, dao.getProductsOfList(1).first().size)

        dao.deleteShoppingList(list)

        // После удаления списка — все его продукты тоже удалены
        val products = dao.getProducts().first()
        assertTrue(products.isEmpty())
    }

    @Test
    fun `deleteShoppingList - cascade does not affect products of other lists`() = runTest {
        val list1 = createShoppingList(id = 1, name = "Groceries")
        val list2 = createShoppingList(id = 2, name = "Hardware")
        dao.addShoppingList(list1)
        dao.addShoppingList(list2)

        dao.addProduct(createProduct(listId = 1, name = "Milk"))
        dao.addProduct(createProduct(listId = 2, name = "Hammer"))

        dao.deleteShoppingList(list1)

        // Продукты второго списка остались
        val remainingProducts = dao.getProducts().first()
        assertEquals(1, remainingProducts.size)
        assertEquals("Hammer", remainingProducts[0].name)
    }

    // ========================
    // Edge cases
    // ========================

    @Test
    fun `addProduct - preserves amount and unit correctly`() = runTest {
        dao.addShoppingList(createShoppingList(id = 1))

        val product = createProduct(
            listId = 1,
            name = "Flour",
            amount = 2.5f,
            unit = MeasurementUnit.KILOGRAM
        )
        dao.addProduct(product)

        val result = dao.getProducts().first()[0]
        assertEquals(2.5f, result.amount)
        assertEquals(MeasurementUnit.KILOGRAM, result.unit)
    }

    @Test
    fun `addShoppingList - preserves icon correctly`() = runTest {
        val list = createShoppingList(
            id = 1,
            name = "Test",
            icon = ShoppingListIcon.entries.last() // берём последний enum
        )
        dao.addShoppingList(list)

        val result = dao.getShoppingLists().first()[0]
        assertEquals(ShoppingListIcon.entries.last(), result.icon)
    }

    // ========================
    // Helper functions
    // ========================

    private fun createShoppingList(
        id: Long = 0,
        name: String = "Test List",
        icon: ShoppingListIcon = ShoppingListIcon.entries.first(),
        addedAtMillis: Long = System.currentTimeMillis()
    ) = ShoppingListEntity(
        id = id,
        name = name,
        icon = icon,
        addedAtMillis = addedAtMillis
    )

    private fun createProduct(
        id: Long = 0,
        listId: Long = 1,
        name: String = "Test Product",
        amount: Float = 1f,
        unit: MeasurementUnit = MeasurementUnit.entries.first(),
        addedAtMillis: Long = System.currentTimeMillis()
    ) = ProductEntity(
        id = id,
        listId = listId,
        name = name,
        amount = amount,
        unit = unit,
        addedAtMillis = addedAtMillis
    )
}