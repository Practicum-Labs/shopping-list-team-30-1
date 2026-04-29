package io.dimasla4ee.shoppinglist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_copy_24
import shoppinglist.composeapp.generated.resources.ic_delete_24
import shoppinglist.composeapp.generated.resources.ic_docs_add_24
import shoppinglist.composeapp.generated.resources.ic_edit_24
import shoppinglist.composeapp.generated.resources.ic_shopping_cart_24

data class ShoppingListItem(
    val name: String,
    val iconRes: DrawableResource
)


@Composable
fun SwipeItem(
    listItem: ShoppingListItem,
    modifier: Modifier = Modifier
) {
    val swipeableState = rememberSwipeToDismissBoxState()
    val scope = rememberCoroutineScope()
    val log by remember {
        derivedStateOf {
            "settledValue: ${swipeableState.settledValue}\ntargetValue: ${swipeableState.targetValue}\ndismissDirection: ${swipeableState.dismissDirection}\nprogress: ${swipeableState.progress}"
        }
    }



    val (color, arrangement) = if (swipeableState.dismissDirection == SwipeToDismissBoxValue.StartToEnd) {
        Color.Transparent to Arrangement.spacedBy(8.dp)
    } else {
        Color(0xFFFF6969) to Arrangement.End
    }
    Text(log)

    SwipeToDismissBox(
        state = swipeableState,
        backgroundContent = {
            Surface(
                modifier = modifier.wrapContentSize(),
                shape = RoundedCornerShape(12.dp),
                color = color
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = arrangement
                ) {
                    when (swipeableState.dismissDirection) {
                        SwipeToDismissBoxValue.StartToEnd -> Actions(
                            { scope.launch { swipeableState.reset() } },
                            { scope.launch { swipeableState.reset() } },
                            { scope.launch { swipeableState.reset() } }
                        )

                        SwipeToDismissBoxValue.EndToStart -> {
                            Icon(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                painter = painterResource(Res.drawable.ic_delete_24),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }

                        SwipeToDismissBoxValue.Settled -> {}
                    }
                }
            }
        }
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = {
                if (swipeableState.settledValue == SwipeToDismissBoxValue.StartToEnd) {
                    scope.launch {
                        swipeableState.reset()
                    }
                } else {
                    //TODO navigate
                }
            }
        ) {
            ItemContent(listItem)
        }
    }
}

@Composable
private fun Actions(
    onEditAction: () -> Unit,
    onCopyAction: () -> Unit,
    onIconAction: () -> Unit
) {
    IconAction(Res.drawable.ic_edit_24) { onEditAction() }
    IconAction(Res.drawable.ic_copy_24) { onCopyAction() }
    IconAction(Res.drawable.ic_docs_add_24) { onIconAction() }
}

@Composable
private fun IconAction(
    res: DrawableResource,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = Color(0xFFEEE0D5)
        ),
        onClick = {
            onClick()
        }
    ) {
        Icon(
            painter = painterResource(res),
            contentDescription = contentDescription
        )
    }
}

@Composable
private fun ItemContent(
    listItem: ShoppingListItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFFEDDBD)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(listItem.iconRes),
                contentDescription = null
            )
        }
        Text(listItem.name)
    }
}

@Preview
@PreviewLightDark
@Composable
private fun SwipeItemPreview() {
    Box(
        modifier = Modifier.height(100.dp).padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        SwipeItem(
            ShoppingListItem(
                "Продукты",
                Res.drawable.ic_shopping_cart_24
            )
        )
    }
}