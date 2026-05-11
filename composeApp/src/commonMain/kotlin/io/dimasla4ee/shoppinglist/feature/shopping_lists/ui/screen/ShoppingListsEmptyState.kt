package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppTypography
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalAppPlaceholders
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.create_list_hint
import shoppinglist.composeapp.generated.resources.no_lists_message

@Composable
fun ShoppingListsEmptyState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(AppDimensions.paddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(LocalAppPlaceholders.current.imgShoppingLists),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(AppDimensions.paddingLarge))

        Text(
            text = stringResource(Res.string.no_lists_message),
            style = AppTypography.labelLarge
        )

        Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))

        Text(
            text = stringResource(Res.string.create_list_hint),
            style = AppTypography.bodyMedium
        )
    }
}
