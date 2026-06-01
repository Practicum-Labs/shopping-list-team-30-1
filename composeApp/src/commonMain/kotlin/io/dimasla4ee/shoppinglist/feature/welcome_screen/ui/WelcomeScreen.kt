package io.dimasla4ee.shoppinglist.feature.welcome_screen.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.utils.OrientationProvider
import io.dimasla4ee.shoppinglist.utils.ScreenOrientation

@Composable
fun WelcomeScreen(
    onGoToShopping: () -> Unit,
    modifier: Modifier = Modifier
) {
    OrientationProvider { orientation ->
        when (orientation) {
            ScreenOrientation.PORTRAIT -> WelcomeScreenPortrait(
                onGoToShopping,
                modifier
            )

            ScreenOrientation.LANDSCAPE -> WelcomeScreenLandscape(
                onGoToShopping,
                modifier
            )
        }
    }
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
