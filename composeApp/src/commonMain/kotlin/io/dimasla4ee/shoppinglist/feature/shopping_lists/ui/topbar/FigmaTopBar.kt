package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.appTopBarColors
import io.dimasla4ee.shoppinglist.core.presentation.components.TopBarAction
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_delete_list_24
import shoppinglist.composeapp.generated.resources.ic_search_24

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FigmaTopBar(
    title: String,
    action1: TopBarAction,
    action2: TopBarAction,
    action3: TopBarAction,
    themeIcon: DrawableResource
) {
    TopAppBar(
        colors = appTopBarColors(),
        modifier = Modifier.padding(end = AppDimensions.paddingVerySmall),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
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
                    painter = painterResource(themeIcon),
                    contentDescription = action1.contentDescription,
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    )
}