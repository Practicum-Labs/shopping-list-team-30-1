package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.AppIconButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.AppIconButtonDefaults
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.ReorderableLazyListState
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_drag_handle_24

@Composable
fun LazyItemScope.ReorderableShoppingItem(
    item: Product,
    state: ReorderableLazyListState,
    hapticFeedback: HapticFeedback,
    onCheckedChange: () -> Unit,
    onLongPress: () -> Unit,
    modifier: Modifier = Modifier,
    showDragHandle: Boolean = true
) {
    ReorderableItem(
        modifier = modifier.combinedClickable(
            onLongClick = onLongPress,
            onClick = onCheckedChange,
            onLongClickLabel = "Редактировать"
        ),
        state = state,
        key = item.id
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShoppingListItem(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = AppDimensions.paddingSmall),
                    item = item,
                    onCheckedChange = onCheckedChange
                )

                if (!showDragHandle) return@ReorderableItem

                AppIconButton(
                    iconRes = Res.drawable.ic_drag_handle_24,
                    colors = AppIconButtonDefaults.appIconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.draggableHandle(
                        onDragStarted = {
                            hapticFeedback.performHapticFeedback(
                                HapticFeedbackType.GestureThresholdActivate
                            )
                        },
                        onDragStopped = {
                            hapticFeedback.performHapticFeedback(
                                HapticFeedbackType.GestureEnd
                            )
                        }
                    ),
                    onClick = {}
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.tertiaryFixed)
        }
    }
}