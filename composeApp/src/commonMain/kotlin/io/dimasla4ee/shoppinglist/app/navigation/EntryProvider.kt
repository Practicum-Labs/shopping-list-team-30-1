@file:Suppress("LongMethod")

package io.dimasla4ee.shoppinglist.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import io.dimasla4ee.shoppinglist.app.startup.session.domain.AppLaunchRepository
import io.dimasla4ee.shoppinglist.app.startup.session.presentation.SessionViewModel
import io.dimasla4ee.shoppinglist.app.startup.session.splash.presentation.SplashEffect
import io.dimasla4ee.shoppinglist.app.startup.session.splash.presentation.SplashIntent
import io.dimasla4ee.shoppinglist.app.startup.session.splash.presentation.SplashViewModel
import io.dimasla4ee.shoppinglist.app.startup.session.splash.ui.SplashScreen
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

const val SPLASH_SCREEN_DELAY = 1000L

fun entryProvider(
    topLevelBackStack: TopLevelBackStack<NavKey>,
    sessionViewModel: SessionViewModel,
    onThemeToggle: () -> Unit,
) = entryProvider<NavKey> {

    entry<Route.Splash> {
        val splashViewModel = koinViewModel<SplashViewModel>()

        SplashScreen(modifier = Modifier.fillMaxSize())

        LaunchedEffect(Unit) {
            delay(SPLASH_SCREEN_DELAY)
            splashViewModel.dispatch(SplashIntent.Initialize)

            splashViewModel.effects.collect { effect ->
                val destination = when (effect) {
                    SplashEffect.NavigateToShoppingLists -> Route.ShoppingLists
                    SplashEffect.NavigateToWelcome -> Route.Welcome
                }
                topLevelBackStack.replaceStack(destination)
            }
        }
    }

    entry<Route.Welcome> {
        val appLaunchRepository = koinInject<AppLaunchRepository>()
        val scope = rememberCoroutineScope()

        WelcomeScreen(
            onGoToShopping = {
                scope.launch { appLaunchRepository.setLaunched() }
                topLevelBackStack.replaceStack(Route.ShoppingLists)
            },
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<Route.ShoppingLists> {
        val viewModel: ShoppingListsViewModel = koinViewModel()
        val state by viewModel.state.collectAsState()
        val sessionState by sessionViewModel.state.collectAsState()
        val isAuthorized = sessionState.isAuthorized

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
                            Route.ProductsList(
                                listId = effect.listId,
                                listName = effect.listName
                            )
                        )
                    }

                    is ShoppingListsEffect.NavigateToAuthorization -> {
                        topLevelBackStack.add(Route.Authorization)
                    }
                }
            }
        }

        ShoppingListsScreen(
            state = state,
            isAuthorized = isAuthorized,
            onIntent = viewModel::dispatch,
            onThemeToggle = onThemeToggle,
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<Route.ProductsList> { route ->
        AddItemRoute(
            listId = route.listId,
            listName = route.listName,
            onBackClick = { topLevelBackStack.removeLast() }
        )
    }

    entry<Route.Authorization> {
        val viewModel = koinViewModel<SignInViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    SignInEffect.NavigateToMain ->
                        topLevelBackStack.removeLast()

                    SignInEffect.NavigateToRecoverPassword ->
                        topLevelBackStack.add(Route.PasswordRecovery)

                    SignInEffect.NavigateToRegister ->
                        topLevelBackStack.add(Route.Registration)

                    SignInEffect.NavigateBack ->
                        topLevelBackStack.removeLast()
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
                    RegisterEffect.NavigateToMain ->
                        topLevelBackStack.replaceStack(Route.ShoppingLists)

                    RegisterEffect.NavigateToSignIn -> topLevelBackStack.removeLast()
                    RegisterEffect.NavigateBack -> topLevelBackStack.removeLast()
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
                    RecoverPasswordEffect.NavigateToSignIn -> topLevelBackStack.removeLast()
                    RecoverPasswordEffect.NavigateBack -> topLevelBackStack.removeLast()
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
