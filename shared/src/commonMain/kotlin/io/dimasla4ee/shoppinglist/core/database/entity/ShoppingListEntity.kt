package io.dimasla4ee.shoppinglist.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon

@Entity
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val icon: ShoppingListIcon,
    val addedAtMillis: Long = System.currentTimeMillis()
)

