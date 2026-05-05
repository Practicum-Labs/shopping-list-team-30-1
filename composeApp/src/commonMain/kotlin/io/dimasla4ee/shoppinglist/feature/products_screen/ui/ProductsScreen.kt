package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.core.presentation.components.AppTopBar
import io.dimasla4ee.shoppinglist.core.presentation.components.TopBarIcon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.add_items_hint
import shoppinglist.composeapp.generated.resources.empty_list_message
import shoppinglist.composeapp.generated.resources.ic_add_56
import shoppinglist.composeapp.generated.resources.ic_arrow_back_24
import shoppinglist.composeapp.generated.resources.ic_menu_24
import shoppinglist.composeapp.generated.resources.img_product_list


@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier
) {
    val fabContainerColor = Color(0xFFFFDCBB)
    val fabIconTint = Color(0xFF2B1700)

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(
                title = "Название списка",
                navigationIcon = {
                    IconButton(onClick = {/* назад */ }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = listOf(
                    TopBarIcon(
                        icon = Res.drawable.ic_menu_24,
                        contentDescription = "Menu",
                        onClick = {}
                    )
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* добавить товар */ },
                containerColor = fabContainerColor) {
                Icon(
                    painter = painterResource(Res.drawable.ic_add_56),
                    contentDescription = "+",
                    tint = fabIconTint
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(Res.drawable.img_product_list),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                modifier = Modifier.padding(horizontal = 44.dp),
                text = stringResource(Res.string.empty_list_message)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 44.dp),
                textAlign = TextAlign.Center,
                text = stringResource(Res.string.add_items_hint)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
private fun ProductsScreenPreview() {
    MaterialTheme {
        ProductsScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}