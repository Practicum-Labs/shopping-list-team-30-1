package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalAppPlaceholders
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.add_items_hint
import shoppinglist.composeapp.generated.resources.empty_list_message

@Composable
fun ItemListPlaceholder(
    modifier: Modifier = Modifier
) {
    // Основной контент экрана
    Column(
        modifier = modifier
            .fillMaxSize()
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
