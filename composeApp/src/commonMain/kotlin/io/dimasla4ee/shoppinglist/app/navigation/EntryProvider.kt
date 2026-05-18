package io.dimasla4ee.shoppinglist.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import io.dimasla4ee.shoppinglist.feature.products_screen.ui.AddItemScreen
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
    entry<Route.Welcome> {
        WelcomeScreen(
            onGoToShopping = { topLevelBackStack.add(Route.ShoppingLists) },
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<Route.ShoppingLists> {
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
                            Route.ProductsList(
                                listId = effect.listId,
                                listName = effect.listName
                            )
                        )
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
