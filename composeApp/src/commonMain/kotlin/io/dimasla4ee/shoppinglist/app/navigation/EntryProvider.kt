package io.dimasla4ee.shoppinglist.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import io.dimasla4ee.shoppinglist.feature.products_screen.ui.AddItemScreen
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsViewModel
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen.ShoppingListsScreen
import io.dimasla4ee.shoppinglist.feature.welcome_screen.ui.WelcomeScreen
import org.koin.compose.viewmodel.koinViewModel

fun entryProvider(
    topLevelBackStack: TopLevelBackStack<NavKey>,
    onThemeToggle: () -> Unit,
) = entryProvider<NavKey> {
    entry<Route.Welcome> {
        WelcomeScreen(
            onGoToShopping = { topLevelBackStack.add(Route.ShoppingLists) },
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<Route.ShoppingLists> {
        val viewModel: ShoppingListsViewModel = koinViewModel()

        LaunchedEffect(Unit) {
            viewModel.observeLists()
        }

        ShoppingListsScreen(
            state = viewModel.state,
            visibleLists = viewModel.visibleLists,
            onFabClick = viewModel::onFabClick,
            onEvent = viewModel::onCardEvent,

            onListClick = { list ->
                topLevelBackStack.add(
                    Route.ProductsList(
                        listId = list.id,
                        listName = list.name
                    )
                )
            },
            onNameChange = viewModel::onNameChange,
            onDismiss = viewModel::onDialogDismiss,
            onConfirm = viewModel::onCreateList,
            onIconSelect = viewModel::onIconSelected,
            onSheetDismiss = viewModel::onSheetDismiss,
            onDeleteAllClick = viewModel::onDeleteAllClick,
            onDeleteAllConfirm = viewModel::onDeleteAllConfirm,
            onDeleteConfirm = viewModel::onDeleteConfirm,
            onRenameValueChange = viewModel::onRenameValueChange,
            onRenameConfirm = viewModel::onRenameConfirm,
            onThemeToggle = onThemeToggle,
            onSearchClick = viewModel::onSearchClick,
            onSearchQueryChange = viewModel::onSearchQueryChange,
            onSearchDismiss = viewModel::onSearchDismiss,
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<Route.ProductsList> { route ->
        AddItemScreen(
            title = route.listName,
            modifier = Modifier.fillMaxSize(),
            onBackClick = {
                topLevelBackStack.removeLast()
            },
            onMenuClick = {
                // TODO(feature-team): добавить клик по Меню, если потребуется
            }
        )
    }

    entry<Route.Authorization> {
        // TODO(feature-team): интегрировать экран авторизации и удалить ScreenPlaceholder
        ScreenPlaceholder("Authorisation")
    }

    entry<Route.Registration> {
        // TODO(feature-team): интегрировать экран регистрации и удалить ScreenPlaceholder
        ScreenPlaceholder("Registration")
    }

    entry<Route.PasswordRecovery> {
        // TODO(feature-team): интегрировать экран восстановления пароля и удалить ScreenPlaceholder
        ScreenPlaceholder("PasswordRecovery")
    }

}

@Composable
private fun ScreenPlaceholder(
    text: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "placeholder\n\n\n$text",
            fontSize = 50.sp,
            color = Color.White
        )
    }
}
