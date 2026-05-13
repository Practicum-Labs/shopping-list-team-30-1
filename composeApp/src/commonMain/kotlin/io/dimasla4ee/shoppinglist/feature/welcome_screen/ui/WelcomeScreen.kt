package io.dimasla4ee.shoppinglist.feature.welcome_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dimasla4ee.shoppinglist.utils.OrientationProvider
import io.dimasla4ee.shoppinglist.utils.ScreenOrientation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_main_logo_78
import shoppinglist.composeapp.generated.resources.welcome_screen_title

@Composable
fun WelcomeScreen(
    onGoToShopping: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (annotatedString, inlineContentMap) = createWelcomeLogo()

    OrientationProvider { orientation ->
        when (orientation) {
            ScreenOrientation.PORTRAIT -> PortraitContent(
                onGoToShopping,
                modifier,
                annotatedString,
                inlineContentMap
            )

            ScreenOrientation.LANDSCAPE -> LandscapeContent(
                onGoToShopping,
                modifier,
                annotatedString,
                inlineContentMap
            )
        }
    }
}

@Composable
fun createWelcomeLogo(): Pair<AnnotatedString, Map<String, InlineTextContent>> {
    val annotatedString = buildAnnotatedString {
        appendInlineContent(id = "imageId")
        append(stringResource(Res.string.welcome_screen_title))
    }

    val inlineContentMap = mapOf(
        "imageId" to InlineTextContent(
            Placeholder(
                width = 78.sp,
                height = 78.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            )
        ) {
            Image(
                modifier = Modifier.offset(y = 2.dp),
                painter = painterResource(Res.drawable.ic_main_logo_78),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                contentDescription = null
            )
        }
    )

    return annotatedString to inlineContentMap
}

@Preview(showSystemUi = true)
@Preview(
    showSystemUi = true,
    device = "spec:width=891dp,height=411dp,dpi=420,orientation=landscape",
    name = "WelcomeScreen_Landscape"
)
@PreviewLightDark
@Composable
private fun WelcomeScreenPreview() {
    ShoppingListTheme {
        WelcomeScreen(
            onGoToShopping = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
