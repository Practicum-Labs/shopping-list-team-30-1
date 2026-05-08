package io.dimasla4ee.shoppinglist.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import io.dimasla4ee.shoppinglist.feature.welcome_screen.ui.WelcomeScreen
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.screen_title

fun entryProvider(topLevelBackStack: TopLevelBackStack<NavKey>) = entryProvider<NavKey> {
    entry<Route.Welcome> {
        WelcomeScreen(
            onGoToShopping = { topLevelBackStack.add(Route.ShoppingLists) },
            modifier = Modifier.fillMaxSize()
        )
    }

    entry<Route.ShoppingLists> {
        // TODO(feature-team): интегрировать экран списков покупок и удалить ScreenPlaceholder
        ScreenPlaceholder(stringResource(Res.string.screen_title))
    }

    entry<Route.ProductsList> {
        // TODO(feature-team): интегрировать экран списка товаров и удалить ScreenPlaceholder
        ScreenPlaceholder("Products")
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
