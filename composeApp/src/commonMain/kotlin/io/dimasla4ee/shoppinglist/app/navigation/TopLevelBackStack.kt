package io.dimasla4ee.shoppinglist.app.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

class TopLevelBackStack<T : NavKey>(private val startKey: T) {
    private var topLevelBackStacks = mutableStateMapOf(
        startKey to mutableStateListOf(startKey)
    )

    var topLevelKey by mutableStateOf(startKey)
        private set

    val backStack: List<T>
        get() {
            return when {
                topLevelKey == startKey -> currentStack

                else -> {
                    val startStack = topLevelBackStacks[startKey] ?: emptyList()
                    startStack + currentStack
                }
            }
        }

    private val currentStack: SnapshotStateList<T>
        get() = topLevelBackStacks[topLevelKey] ?: SnapshotStateList()

    fun add(key: T) {
        currentStack.add(key)
    }

    fun removeLast() {
        when {
            currentStack.size > 1 -> currentStack.removeLastOrNull()
            topLevelKey != startKey -> topLevelKey = startKey
        }
    }

    fun replaceStack(vararg keys: T) {
        require(keys.isNotEmpty())

        topLevelBackStacks[topLevelKey] = mutableStateListOf(*keys)
    }

}
