package io.dimasla4ee.shoppinglist.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = ["id"],
            childColumns = ["listId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val listId: Long,
    val name: String,
    val amount: String,
    val unit: String,
    val isChecked: Boolean = false,
    val position: Int = 0,
    val addedAtMillis: Long = System.currentTimeMillis()
)