package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppTypography
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalAppPlaceholders
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.search_hint
import shoppinglist.composeapp.generated.resources.search_not_found

@Composable
fun ShoppingListsSearchEmptyState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(AppDimensions.paddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(AppDimensions.paddingVeryBig))

        Image(
            painter = painterResource(LocalAppPlaceholders.current.imgSearchScreen),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(AppDimensions.paddingVeryBig))

        Text(
            text = stringResource(Res.string.search_not_found),
            style = AppTypography.labelLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))

        Text(
            text = stringResource(Res.string.search_hint),
            style = AppTypography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}