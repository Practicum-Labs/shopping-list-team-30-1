package io.dimasla4ee.shoppinglist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.components.ShoppingListItem
import io.dimasla4ee.shoppinglist.components.SwipeItem
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_add_circle_24
import shoppinglist.composeapp.generated.resources.ic_remove_circle_24
import shoppinglist.composeapp.generated.resources.ic_shopping_cart_24
import shoppinglist.composeapp.generated.resources.img_main_screen

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val iconRes = remember(showContent) {
            if (showContent) Res.drawable.ic_add_circle_24 else Res.drawable.ic_remove_circle_24
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SwipeItem(
                ShoppingListItem(
                    "Продукты",
                    Res.drawable.ic_shopping_cart_24
                ),
                Modifier.padding(40.dp).width(300.dp)
            )
            IconButton(
                onClick = { showContent = !showContent }
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null
                )
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.img_main_screen),
                        contentDescription = null
                    )
                    Text("Compose: $greeting")
                }
            }
        }
    }
}