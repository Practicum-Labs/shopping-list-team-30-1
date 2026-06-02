package io.dimasla4ee.shoppinglist.core.presentation.components.fab_menu

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.FloatingActionButtonMenuScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FloatingActionButtonMenuScope.AppFloatingActionButtonMenuItem(
    onClick: () -> Unit,
    painter: Painter,
    label: String,
    modifier: Modifier = Modifier
) = FloatingActionButtonMenuItem(
    modifier = modifier,
    onClick = onClick,
    text = { Text(text = label, style = MaterialTheme.typography.labelMedium) },
    icon = { Icon(painter = painter, contentDescription = null) }
)