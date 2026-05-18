package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractor
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListsState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShoppingListsViewModel(
    private val interactor: ShoppingListsInteractor
) : MviViewModel<
        ShoppingListsIntent,
        ShoppingListsState,
        ShoppingListsEffect
        >(
    ShoppingListsState()
) {
    private var observeJob: Job? = null

    override fun reduce(
        intent: ShoppingListsIntent,
        current: ShoppingListsState
    ): ShoppingListsState {

        return when (intent) {
            ShoppingListsIntent.FabClick -> {
                current.copy(
                    dialog = ShoppingListDialog.Create
                )
            }

            is ShoppingListsIntent.NameChanged -> {
                current.copy(
                    newListName = intent.value
                )
            }

            ShoppingListsIntent.DialogDismiss -> {
                current.copy(
                    dialog = ShoppingListDialog.None,
                    newListName = "",
                    renameValue = "",
                    selectedListId = null,
                    isIconSheetVisible = false
                )
            }

            ShoppingListsIntent.DeleteAllClick -> {
                current.copy(
                    dialog = ShoppingListDialog.DeleteAll
                )
            }

            ShoppingListsIntent.SearchClick -> {
                current.copy(
                    isSearchMode = true,
                    isFabVisible = false
                )
            }

            ShoppingListsIntent.SearchDismiss -> {
                current.copy(
                    isSearchMode = false,
                    isFabVisible = true,
                    searchQuery = ""
                )
            }

            is ShoppingListsIntent.SearchQueryChanged -> {
                current.copy(
                    searchQuery = intent.value
                )
            }

            is ShoppingListsIntent.RenameValueChanged -> {
                current.copy(
                    renameValue = intent.value
                )
            }

            is ShoppingListsIntent.EditClicked -> {
                current.copy(
                    dialog = ShoppingListDialog.Rename(
                        id = intent.item.id,
                        value = intent.item.name
                    ),
                    renameValue = intent.item.name
                )
            }

            is ShoppingListsIntent.DeleteClicked -> {
                current.copy(
                    dialog = ShoppingListDialog.Delete(
                        id = intent.item.id,
                        name = intent.item.name
                    )
                )
            }

            is ShoppingListsIntent.ChangeIconClicked -> {
                current.copy(
                    isIconSheetVisible = true,
                    selectedListId = intent.item.id
                )
            }

            ShoppingListsIntent.SheetDismiss -> {
                current.copy(
                    isIconSheetVisible = false,
                    selectedListId = null
                )
            }

            else -> current
        }
    }

    override suspend fun handleIntent(intent: ShoppingListsIntent) {

        when (intent) {
            ShoppingListsIntent.ObserveLists -> {
                observeLists()
            }

            ShoppingListsIntent.CreateList -> {
                val name = state.value.newListName.trim()
                if (name.isEmpty()) return
                interactor.addShoppingList(name)
                updateState {
                    it.copy(
                        dialog = ShoppingListDialog.None,
                        newListName = ""
                    )
                }
            }

            ShoppingListsIntent.DeleteAllConfirm -> {
                interactor.deleteAllShoppingLists()

                dispatch(ShoppingListsIntent.DialogDismiss)
            }

            is ShoppingListsIntent.CopyClicked -> {

                val target = findListById(
                    intent.item.id
                ) ?: return

                interactor.duplicateShoppingList(target)
            }

            is ShoppingListsIntent.IconSelected -> {

                val selectedId = state.value.selectedListId ?: return

                val target = findListById(
                    selectedId
                ) ?: return

                interactor.updateShoppingList(
                    target.copy(icon = intent.icon)
                )

                dispatch(ShoppingListsIntent.SheetDismiss)
            }

            ShoppingListsIntent.RenameConfirm -> {

                val dialog = state.value.dialog as? ShoppingListDialog.Rename
                    ?: return

                val name = state.value.renameValue.trim()

                if (name.isEmpty()) return

                val oldList = findListById(
                    dialog.id
                ) ?: return

                interactor.updateShoppingList(
                    oldList.copy(name = name)
                )

                dispatch(ShoppingListsIntent.DialogDismiss)
            }

            ShoppingListsIntent.DeleteConfirm -> {

                val dialog = state.value.dialog as? ShoppingListDialog.Delete
                    ?: return

                val target = findListById(
                    dialog.id
                ) ?: return

                interactor.deleteShoppingList(target)

                dispatch(ShoppingListsIntent.DialogDismiss)
            }

            is ShoppingListsIntent.ListClicked -> {

                emitEffect(
                    ShoppingListsEffect.NavigateToProducts(
                        listId = intent.item.id,
                        listName = intent.item.name
                    )
                )
            }

            else -> Unit
        }
    }

    private suspend fun observeLists() {
        if (observeJob != null) return

        observeJob = launchCollectJob()
    }

    private fun launchCollectJob() = kotlinx.coroutines.CoroutineScope(
        kotlinx.coroutines.Dispatchers.Main
    ).launch {
        interactor.getShoppingLists().collectLatest { list ->
            updateState {
                it.copy(
                    lists = list
                )
            }
        }
    }

    private fun findListById(id: Long) =
        state.value.lists.find { it.id == id }
}