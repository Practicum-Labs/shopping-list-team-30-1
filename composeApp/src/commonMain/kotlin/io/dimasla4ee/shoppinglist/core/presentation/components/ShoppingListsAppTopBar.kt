package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppTypography
import io.dimasla4ee.shoppinglist.app.ui.theme.appTopBarColors
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_delete_list_24
import shoppinglist.composeapp.generated.resources.ic_fab_24
import shoppinglist.composeapp.generated.resources.ic_search_24
import shoppinglist.composeapp.generated.resources.ic_theme_24

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListsScaffold(
    title: String,

    action1: TopBarAction,
    action2: TopBarAction,
    action3: TopBarAction,

    onFabClick: () -> Unit,

    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = appTopBarColors(),
                modifier = modifier.padding(end = AppDimensions.endPaddingTopBar),
                title = {
                    Text(
                        text = title,
                        style = AppTypography.titleMedium
                    )
                },
                actions = {
                    IconButton(onClick = action1.onClick) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_search_24),
                            contentDescription = action1.contentDescription,
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                    }

                    IconButton(onClick = action2.onClick) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_delete_list_24),
                            contentDescription = action1.contentDescription,
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                    }

                    IconButton(onClick = action3.onClick) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_theme_24),
                            contentDescription = action1.contentDescription,
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.offset(y = (-AppDimensions.paddingMedium)),
                shape = RoundedCornerShape(AppDimensions.paddingMedium),
                onClick = onFabClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_fab_24),
                    contentDescription = null,
                )
            }
        },
        content = content
    )
}

data class TopBarAction(
    val contentDescription: String? = null,
    val onClick: () -> Unit
)