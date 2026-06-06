package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalAppPlaceholders
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.search_hint
import shoppinglist.shared.generated.resources.search_not_found

@Composable
fun ShoppingListsSearchEmptyPortrait(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = AppDimensions.paddingVeryLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(AppDimensions.spacerBig))

        Image(
            painter = painterResource(LocalAppPlaceholders.current.imgSearchScreen),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(AppDimensions.spacerBig))

        Text(
            text = stringResource(Res.string.search_not_found),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppDimensions.spacerSmall))

        Text(
            text = stringResource(Res.string.search_hint),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(
    showSystemUi = true,
    name = "ShoppingListsSearchEmpty_Portrait"
)
@Composable
private fun ShoppingListsSearchEmptyPortraitPreview() {
    ShoppingListTheme {
        ShoppingListsSearchEmptyPortrait()
    }
}
