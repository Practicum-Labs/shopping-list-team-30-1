package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.RemoveSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.ClearAuthTokensUseCase
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractor
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShoppingListsViewModel(
    private val interactor: ShoppingListsInteractor,
    private val removeSortModeUseCase: RemoveSortModeUseCase,
    private val clearAuthTokensUseCase: ClearAuthTokensUseCase
) : MviViewModel<ShoppingListsIntent, ShoppingListsState, ShoppingListsEffect>(
    initialState = ShoppingListsState()
) {
    private var observeJob: Job? = null

    override fun reduce(
        intent: ShoppingListsIntent,
        current: ShoppingListsState
    ): ShoppingListsState = when (intent) {
        ShoppingListsIntent.FabClick -> current.copy(dialog = ShoppingListDialog.Create)
        is ShoppingListsIntent.NameChanged -> current.copy(newListName = intent.value)
        ShoppingListsIntent.DialogDismiss -> reduceDialogDismiss(current)
        ShoppingListsIntent.DeleteAllClick -> reduceDeleteAllClick(current)
        ShoppingListsIntent.SearchClick -> reduceSearchClick(current)
        ShoppingListsIntent.SearchDismiss -> reduceSearchDismiss(current)
        is ShoppingListsIntent.SearchQueryChanged -> reduceSearchQueryChanged(intent, current)
        is ShoppingListsIntent.RenameValueChanged -> reduceRenameValueChanged(intent, current)
        is ShoppingListsIntent.EditClicked -> reduceEditClicked(intent, current)
        is ShoppingListsIntent.DeleteClicked -> reduceDeleteClicked(intent, current)
        is ShoppingListsIntent.ChangeIconClicked -> reduceChangeIconClicked(intent, current)
        ShoppingListsIntent.SheetDismiss -> reduceSheetDismiss(current)

        else -> current
    }

    private fun reduceDialogDismiss(
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            dialog = ShoppingListDialog.None,
            newListName = "",
            renameValue = "",
            selectedListId = null,
            isIconSheetVisible = false
        )
    }

    private fun reduceDeleteAllClick(
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            dialog = ShoppingListDialog.DeleteAll
        )
    }

    private fun reduceSearchClick(
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            isSearchMode = true,
            isFabVisible = false
        )
    }

    private fun reduceSearchDismiss(
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            isSearchMode = false,
            isFabVisible = true,
            searchQuery = ""
        )
    }

    private fun reduceSearchQueryChanged(
        intent: ShoppingListsIntent.SearchQueryChanged,
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            searchQuery = intent.value
        )
    }

    private fun reduceEditClicked(
        intent: ShoppingListsIntent.EditClicked,
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            dialog = ShoppingListDialog.Rename(
                id = intent.item.id,
                value = intent.item.name
            ),
            renameValue = intent.item.name
        )
    }

    private fun reduceDeleteClicked(
        intent: ShoppingListsIntent.DeleteClicked,
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            dialog = ShoppingListDialog.Delete(
                id = intent.item.id,
                name = intent.item.name
            )
        )
    }

    private fun reduceChangeIconClicked(
        intent: ShoppingListsIntent.ChangeIconClicked,
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            isIconSheetVisible = true,
            selectedListId = intent.item.id
        )
    }

    private fun reduceRenameValueChanged(
        intent: ShoppingListsIntent.RenameValueChanged,
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            renameValue = intent.value
        )
    }

    private fun reduceSheetDismiss(
        current: ShoppingListsState
    ): ShoppingListsState {

        return current.copy(
            isIconSheetVisible = false,
            selectedListId = null
        )
    }

    override suspend fun handleIntent(
        intent: ShoppingListsIntent
    ) {
        when (intent) {
            ShoppingListsIntent.ObserveLists -> observeLists()
            ShoppingListsIntent.CreateList -> handleCreateList()
            ShoppingListsIntent.DeleteAllConfirm -> handleDeleteAllConfirm()
            is ShoppingListsIntent.CopyClicked -> handleCopyClicked(intent)
            is ShoppingListsIntent.IconSelected -> handleIconSelected(intent)
            ShoppingListsIntent.RenameConfirm -> handleRenameConfirm()
            ShoppingListsIntent.DeleteConfirm -> handleDeleteConfirm()
            is ShoppingListsIntent.ListClicked -> handleListClicked(intent)
            is ShoppingListsIntent.LogoutClick -> handleLogoutClick()
            is ShoppingListsIntent.AuthorizationClicked ->
                handleAuthorizationClicked(intent.isAuthorized)

            else -> Unit
        }
    }

    private suspend fun handleAuthorizationClicked(isAuthorized: Boolean) = when (isAuthorized) {
        true -> updateState { it.copy(dialog = ShoppingListDialog.Logout) }
        false -> emitEffect(ShoppingListsEffect.NavigateToAuthorization)
    }

    private suspend fun handleLogoutClick() {
        clearAuthTokensUseCase()
        dispatch(ShoppingListsIntent.DialogDismiss)
    }

    private suspend fun handleCreateList() {
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

    private suspend fun handleDeleteAllConfirm() {
        interactor.deleteAllShoppingLists()
        dispatch(ShoppingListsIntent.DialogDismiss)
    }

    private suspend fun handleCopyClicked(
        intent: ShoppingListsIntent.CopyClicked
    ) {
        val target = findListById(
            intent.item.id
        ) ?: return

        interactor.duplicateShoppingList(target)
    }

    private suspend fun handleIconSelected(
        intent: ShoppingListsIntent.IconSelected
    ) {
        val selectedId = state.value.selectedListId ?: return
        val target = findListById(selectedId) ?: return

        interactor.updateShoppingList(
            target.copy(icon = intent.icon)
        )

        dispatch(ShoppingListsIntent.SheetDismiss)
    }

    private suspend fun handleRenameConfirm() {
        val dialog = state.value.dialog
                as? ShoppingListDialog.Rename
            ?: return

        val name = state.value.renameValue.trim()

        if (name.isEmpty()) return

        val oldList = findListById(dialog.id) ?: return

        interactor.updateShoppingList(
            oldList.copy(name = name)
        )

        dispatch(ShoppingListsIntent.DialogDismiss)
    }

    private suspend fun handleDeleteConfirm() {

        val dialog = state.value.dialog
                as? ShoppingListDialog.Delete
            ?: return

        val target = findListById(dialog.id)
            ?: return

        interactor.deleteShoppingList(target)
        removeSortModeUseCase(dialog.id)

        dispatch(
            ShoppingListsIntent.DialogDismiss
        )
    }

    private suspend fun handleListClicked(
        intent: ShoppingListsIntent.ListClicked
    ) {
        emitEffect(
            ShoppingListsEffect.NavigateToProducts(
                listId = intent.item.id,
                listName = intent.item.name
            )
        )
    }

    private fun observeLists() {
        if (observeJob != null) return

        observeJob = launchCollectJob()
    }

    private fun launchCollectJob() = CoroutineScope(Dispatchers.Main).launch {
        interactor.getShoppingLists().collectLatest { list ->
            updateState {
                it.copy(
                    lists = list.toList(),
                    isLoading = false
                )
            }
        }
    }

    private fun findListById(id: Long) =
        state.value.lists.find { it.id == id }
}