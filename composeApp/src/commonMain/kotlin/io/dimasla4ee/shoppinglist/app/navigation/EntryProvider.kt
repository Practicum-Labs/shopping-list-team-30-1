package io.dimasla4ee.shoppinglist.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordEffect
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordViewModel
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.register.RegisterEffect
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.register.RegisterViewModel
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in.SignInEffect
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in.SignInViewModel
import io.dimasla4ee.shoppinglist.feature.authorization.ui.recover_password.RecoverPasswordScreen
import io.dimasla4ee.shoppinglist.feature.authorization.ui.register.RegisterScreen
import io.dimasla4ee.shoppinglist.feature.authorization.ui.sign_in.SignInScreen
import io.dimasla4ee.shoppinglist.feature.products_screen.ui.AddItemRoute
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

        AddItemRoute(
            listName = route.listName,
            onMenuClick = {},
            onBackClick = {
                topLevelBackStack.removeLast()
            }
        )
    }

    entry<Route.Authorization> {
        val viewModel = koinViewModel<SignInViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    SignInEffect.NavigateToMain -> {
                        topLevelBackStack.replaceStack(Route.ShoppingLists)
                    }

                    SignInEffect.NavigateToRecoverPassword -> {
                        topLevelBackStack.add(Route.PasswordRecovery)
                    }

                    SignInEffect.NavigateToRegister -> {
                        topLevelBackStack.add(Route.Registration)
                    }
                }
            }
        }

        SignInScreen(
            state = state,
            onIntent = viewModel::dispatch,
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<Route.Registration> {
        val viewModel = koinViewModel<RegisterViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    RegisterEffect.NavigateToMain -> {
                        topLevelBackStack.replaceStack(Route.ShoppingLists)
                    }

                    RegisterEffect.NavigateToSignIn -> {
                        topLevelBackStack.removeLast()
                    }
                }
            }
        }

        RegisterScreen(
            state = state,
            onIntent = viewModel::dispatch,
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<Route.PasswordRecovery> {
        val viewModel = koinViewModel<RecoverPasswordViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    RecoverPasswordEffect.NavigateToSignIn -> {
                        topLevelBackStack.removeLast()
                    }
                }
            }
        }

        RecoverPasswordScreen(
            state = state,
            onIntent = viewModel::dispatch,
            modifier = Modifier.fillMaxSize()
        )
    }

}
