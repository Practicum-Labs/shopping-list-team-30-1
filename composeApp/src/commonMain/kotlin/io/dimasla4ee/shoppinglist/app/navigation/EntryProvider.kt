package io.dimasla4ee.shoppinglist.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import io.dimasla4ee.shoppinglist.app.navigation.Route.Authorization
import io.dimasla4ee.shoppinglist.app.navigation.Route.PasswordRecovery
import io.dimasla4ee.shoppinglist.app.navigation.Route.ProductsList
import io.dimasla4ee.shoppinglist.app.navigation.Route.Registration
import io.dimasla4ee.shoppinglist.app.navigation.Route.ShoppingLists
import io.dimasla4ee.shoppinglist.app.navigation.Route.Welcome
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
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsEffect
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsIntent
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsViewModel
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen.ShoppingListsScreen
import io.dimasla4ee.shoppinglist.feature.welcome_screen.ui.WelcomeScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

fun entryProvider(
    topLevelBackStack: TopLevelBackStack<NavKey>,
    onThemeToggle: () -> Unit,
) = entryProvider<NavKey> {
    entry<Welcome> {
        WelcomeScreen(
            onGoToShopping = { topLevelBackStack.add(Authorization) },
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<ShoppingLists> {
        val viewModel: ShoppingListsViewModel = koinViewModel()

        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {

            viewModel.dispatch(
                ShoppingListsIntent.ObserveLists
            )
        }

        LaunchedEffect(Unit) {
            viewModel.effects.collectLatest { effect ->
                when (effect) {
                    is ShoppingListsEffect.NavigateToProducts -> {
                        topLevelBackStack.add(
                            ProductsList(
                                listId = effect.listId,
                                listName = effect.listName
                            )
                        )
                    }

                    is ShoppingListsEffect.NavigateToAuthorization -> {
                        topLevelBackStack.replaceStack(Authorization)
                    }
                }
            }
        }

        ShoppingListsScreen(
            state = state,
            onIntent = viewModel::dispatch,
            onThemeToggle = onThemeToggle,
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<ProductsList> { route ->

        AddItemRoute(
            listId = route.listId,
            listName = route.listName,
            onMenuClick = {},
            onBackClick = {
                topLevelBackStack.removeLast()
            }
        )
    }

    entry<Authorization> {
        val viewModel = koinViewModel<SignInViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    SignInEffect.NavigateToMain -> {
                        topLevelBackStack.replaceStack(ShoppingLists)
                    }

                    SignInEffect.NavigateToRecoverPassword -> {
                        topLevelBackStack.add(PasswordRecovery)
                    }

                    SignInEffect.NavigateToRegister -> {
                        topLevelBackStack.add(Registration)
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

    entry<Registration> {
        val viewModel = koinViewModel<RegisterViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    RegisterEffect.NavigateToMain -> {
                        topLevelBackStack.replaceStack(ShoppingLists)
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

    entry<PasswordRecovery> {
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
