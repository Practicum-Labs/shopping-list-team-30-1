package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_add_circle_24
import shoppinglist.composeapp.generated.resources.ic_arrow_back_16


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigationIcon: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (onNavigationIcon != null) {
                IconButton(onClick = onNavigationIcon) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_add_circle_24),
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { /* клик */ }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_add_circle_24),
                    contentDescription = "Много"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AppTopBarPreview() {
    MaterialTheme {
        AppTopBar(
            title = "Название списка",
            onNavigationIcon = {}
        )
    }
}

//data class TopBarIcon(
//    val iconResId: Int,
//    val contentDescription: String? = null,
//    val onClick: () -> Unit
//)