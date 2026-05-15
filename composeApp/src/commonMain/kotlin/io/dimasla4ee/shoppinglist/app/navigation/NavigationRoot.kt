package io.dimasla4ee.shoppinglist.app.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.viewmodel.koinViewModel
import kotlin.collections.listOf

@Composable
fun NavigationRoot(
    onThemeToggle: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NavigationViewModel = koinViewModel()
) {
    val topLevelBackStack = viewModel.backStack
    val entryProvider = remember(
        topLevelBackStack,
        onThemeToggle
    ) {
        entryProvider(
            topLevelBackStack = topLevelBackStack,
            onThemeToggle = onThemeToggle
        )
    }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets()
    ) { innerPaddings ->
        NavDisplay(
            modifier = Modifier.padding(innerPaddings),
            backStack = topLevelBackStack.backStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            onBack = { topLevelBackStack.removeLast() },
            entryProvider = entryProvider,
            transitionSpec = { NavigationTransitions.forward() },
            popTransitionSpec = { NavigationTransitions.back() },
            predictivePopTransitionSpec = { NavigationTransitions.predictiveBack() }
        )
    }
}