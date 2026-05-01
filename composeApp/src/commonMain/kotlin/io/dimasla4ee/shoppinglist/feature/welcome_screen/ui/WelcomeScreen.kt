package io.dimasla4ee.shoppinglist.feature.welcome_screen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_list_24
import shoppinglist.composeapp.generated.resources.img_main_screen
import shoppinglist.composeapp.generated.resources.onboard_instruction
import shoppinglist.composeapp.generated.resources.onboard_welcome_message
import shoppinglist.composeapp.generated.resources.welcome_screen_title

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onGoToShopping: () -> Unit
) {
    var showContent by remember { mutableStateOf(true) }
    var clicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 22.dp, bottom = 100.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_list_24),
                modifier = Modifier.size(78.dp),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(stringResource(Res.string.welcome_screen_title))
        }

        AnimatedVisibility(visible = showContent) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Image(
                    painter = painterResource(Res.drawable.img_main_screen),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(start = 44.dp, end = 44.dp, top = 48.dp),
                    text = stringResource(Res.string.onboard_welcome_message)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.padding(start = 44.dp, end = 44.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(Res.string.onboard_instruction)
                )
            }
        }

        Button(
            onClick = {
                if (!clicked) {
                    clicked = true
                    onGoToShopping()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "За покупками")
        }
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen(
            modifier = Modifier.fillMaxSize(),
            onGoToShopping = {}
        )
    }
}