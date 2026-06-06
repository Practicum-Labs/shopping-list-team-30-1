package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.ic_main_logo_78
import shoppinglist.shared.generated.resources.welcome_screen_title

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ShoppingListLogo(
    onWidth: (Dp) -> Unit,
    modifier: Modifier = Modifier
) {
    val (annotatedString, inlineContentMap) = createWelcomeLogo()
    val density = LocalDensity.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            inlineContent = inlineContentMap,
            text = annotatedString,
            style = MaterialTheme.typography.titleLargeEmphasized,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(end = AppDimensions.paddingMedium)
                .onGloballyPositioned { coordinates ->
                    val width = with(density) { coordinates.size.width.toDp() }
                    onWidth(width)
                },
        )
    }
}

@Composable
private fun createWelcomeLogo(): Pair<AnnotatedString, Map<String, InlineTextContent>> {
    val annotatedString = buildAnnotatedString {
        appendInlineContent(id = "imageId")
        append(stringResource(Res.string.welcome_screen_title))
    }

    val inlineContentMap = mapOf(
        "imageId" to InlineTextContent(
            Placeholder(
                width = AppDimensions.logoSize,
                height = AppDimensions.logoSize,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            )
        ) {
            Image(
                modifier = Modifier.offset(y = AppDimensions.logoOffset),
                painter = painterResource(Res.drawable.ic_main_logo_78),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                contentDescription = null
            )
        }
    )

    return annotatedString to inlineContentMap
}
