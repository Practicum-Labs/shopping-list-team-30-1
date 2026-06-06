package io.dimasla4ee.shoppinglist.feature.welcome_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalAppPlaceholders
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.components.ShoppingListLogo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.onboard_instruction
import shoppinglist.shared.generated.resources.onboard_welcome_message
import shoppinglist.shared.generated.resources.shopping

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WelcomeScreenPortrait(
    onGoToShopping: () -> Unit,
    modifier: Modifier = Modifier
) {
    var clicked by remember { mutableStateOf(false) }
    var logoWidth by remember { mutableStateOf(0.dp) }

    Column(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(vertical = AppDimensions.paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ShoppingListLogo({ width -> logoWidth = width })

        Box(
            modifier = Modifier.weight(1F),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = AppDimensions.paddingLarge),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(LocalAppPlaceholders.current.imgMainScreen),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(AppDimensions.spacerVeryBig))

                Text(
                    text = stringResource(Res.string.onboard_welcome_message),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(AppDimensions.spacerSmall))

                Text(
                    text = stringResource(Res.string.onboard_instruction),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(AppDimensions.spacerSmall))

                Button(
                    onClick = {
                        if (!clicked) {
                            clicked = true
                            onGoToShopping()
                        }
                    },
                    modifier = Modifier
                        .widthIn(max = logoWidth)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(Res.string.shopping))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun WelcomeScreenPortraitPreview() {
    ShoppingListTheme {
        WelcomeScreenPortrait(
            onGoToShopping = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
