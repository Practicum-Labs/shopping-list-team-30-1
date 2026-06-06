package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalAppPlaceholders
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.create_list_hint
import shoppinglist.shared.generated.resources.no_lists_message

@Composable
fun ShoppingListsEmptyLandscape(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize()
                .padding(AppDimensions.paddingMedium),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = painterResource(LocalAppPlaceholders.current.imgShoppingLists),
                contentDescription = null
            )
        }

        Box(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize()
                .padding(AppDimensions.paddingMedium),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.no_lists_message),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(AppDimensions.spacerSmall))

                Text(
                    text = stringResource(Res.string.create_list_hint),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    device = "spec:width=891dp,height=411dp,dpi=420,orientation=landscape",
    name = "ShoppingListsEmpty_Landscape"
)
@Composable
private fun ShoppingListsEmptyLandscapePreview() {
    ShoppingListTheme {
        ShoppingListsEmptyLandscape()
    }
}
