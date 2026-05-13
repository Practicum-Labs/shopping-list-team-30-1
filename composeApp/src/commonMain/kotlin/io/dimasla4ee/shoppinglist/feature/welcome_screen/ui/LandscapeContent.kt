package io.dimasla4ee.shoppinglist.feature.welcome_screen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppTypography
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalAppPlaceholders
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_main_logo_78
import shoppinglist.composeapp.generated.resources.onboard_instruction
import shoppinglist.composeapp.generated.resources.onboard_welcome_message
import shoppinglist.composeapp.generated.resources.shopping
import shoppinglist.composeapp.generated.resources.welcome_screen_title

@Composable
fun LandscapeContent(
    onGoToShopping: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showContent by remember { mutableStateOf(true) }
    var clicked by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    var logoWidth by remember { mutableStateOf(0.dp) }
    var imgWidth by remember { mutableStateOf(0.dp) }

    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(AppDimensions.paddingMedium)
    ) {
        Box(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(LocalAppPlaceholders.current.imgMainScreen),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned { coordinates ->
                        imgWidth = with(density) {
                            coordinates.size.width.toDp()
                        }
                    },
                contentScale = ContentScale.Fit
            )
        }

        Box(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .heightIn(max = imgWidth)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1F)
                        .onGloballyPositioned { coordinates ->
                            logoWidth = with(density) {
                                coordinates.size.width.toDp()
                            }
                        }
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_main_logo_78),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                        contentDescription = null
                    )

                    Text(
                        text = stringResource(Res.string.welcome_screen_title),
                        style = AppTypography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                AnimatedVisibility(
                    visible = showContent,
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = AppDimensions.paddingLarge)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(Res.string.onboard_welcome_message),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = AppTypography.labelLarge
                        )

                        Spacer(modifier = Modifier.height(AppDimensions.spacerSmall))

                        Text(
                            text = stringResource(Res.string.onboard_instruction),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = AppTypography.bodyMedium
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
    }
}

@Preview(
    showSystemUi = true,
    name = "WelcomeScreen_Landscape",
    device = "spec:width=891dp,height=411dp,dpi=420,orientation=landscape"
)
@Composable
private fun LandscapeContentPreview() {
    ShoppingListTheme {
        LandscapeContent(
            onGoToShopping = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}