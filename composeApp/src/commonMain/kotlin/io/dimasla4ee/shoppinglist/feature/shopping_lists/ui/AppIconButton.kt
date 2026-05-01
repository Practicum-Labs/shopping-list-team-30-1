package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * Стилизованная кнопка с иконкой, используемая в приложении как основное действие.
 *
 * Компонент автоматически настраивает состояние "выключено" (disabled), если [onClick] равен null.
 * Использует специфическую цветовую схему [AppIconButtonDefaults], адаптированную под дизайн приложения.
 *
 * @param iconRes Ресурс иконки для отрисовки.
 * @param modifier Модификатор для настройки макета кнопки (размер, отступы и т.д.).
 * @param contentDescription Описание контента для специальных возможностей (Accessibility).
 * @param onClick Коллбэк, вызываемый при нажатии. Если передано null, кнопка становится неактивной.
 */
@Composable
fun AppIconButton(
    iconRes: DrawableResource,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    onClick: (() -> Unit)? = null
) {
    IconButton(
        modifier = modifier,
        colors = AppIconButtonDefaults.iconActionColors(),
        onClick = { onClick?.invoke() },
        enabled = onClick != null
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription
        )
    }
}

