package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import shoppinglist.shared.generated.resources.add_items_hint
import shoppinglist.shared.generated.resources.empty_list_message

@Composable
fun ItemListPlaceholderPortrait(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = AppDimensions.paddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(LocalAppPlaceholders.current.imgProductList),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(AppDimensions.spacerVeryBig))

        Text(
            textAlign = TextAlign.Center,
            text = stringResource(Res.string.empty_list_message),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(AppDimensions.spacerSmall))

        Text(
            textAlign = TextAlign.Center,
            text = stringResource(Res.string.add_items_hint),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(
    showSystemUi = true,
    name = "ItemListPlaceholderPortrait"
)
@Composable
private fun ItemListPlaceholderPortraitPreview() {
    ShoppingListTheme {
        ItemListPlaceholderPortrait(Modifier.fillMaxSize())
    }
}
