package io.dimasla4ee.shoppinglist.core.presentation.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.core.utils.TestOnlyPurpose
import kotlin.enums.EnumEntries

/**
 * Панель переключения вариантов для A/B-тестирования интерфейсов.
 *
 * Отображает горизонтальный ряд чипов со всеми значениями перечисления.
 * Текущий вариант визуально выделяется цветом.
 *
 * @param T тип перечисления вариантов
 * @param enumEntries все варианты (например, `MyEnum.entries`)
 * @param currentValue текущий выбранный вариант
 * @param onClick вызывается при выборе другого варианта
 * @param modifier модификатор компонента
 *
 * Пример:
 * ```
 * var variant by remember { mutableStateOf(Variant.A) }
 * EnumSwitcherRow(Variant.entries, variant, { variant = it })
 * ```
 */
@TestOnlyPurpose
@Composable
fun <T : Enum<T>> EnumSwitcherRow(
    enumEntries: EnumEntries<T>,
    currentValue: T,
    onClick: (newValue: T) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        tonalElevation = 3.dp,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            enumEntries.forEach { entry ->
                val isSelected = currentValue == entry
                AssistChip(
                    onClick = { onClick(entry) },
                    label = { Text(entry.name, style = MaterialTheme.typography.labelSmall) },
                    modifier = Modifier.padding(horizontal = 4.dp),
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.surfaceVariant,
                        labelColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    border = if (isSelected) null else null
                )
            }
        }
    }
}