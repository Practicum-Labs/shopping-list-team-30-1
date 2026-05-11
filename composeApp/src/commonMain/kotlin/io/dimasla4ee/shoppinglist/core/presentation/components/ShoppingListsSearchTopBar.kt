package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import io.dimasla4ee.shoppinglist.app.ui.theme.AppTypography
import io.dimasla4ee.shoppinglist.app.ui.theme.appTopBarColors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.hint_search_list
import shoppinglist.composeapp.generated.resources.ic_arrow_back_24
import shoppinglist.composeapp.generated.resources.ic_close_search_24

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListsScaffoldSearch(
    query: String,
    onQueryChange: (String) -> Unit,

    onBack: () -> Unit,
    onClear: () -> Unit,
    onSearch: () -> Unit = {},

    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isPreview = LocalInspectionMode.current

    if (!isPreview) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,

        topBar = {

            TopAppBar(
                colors = appTopBarColors(),

                navigationIcon = {
                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                            onBack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back_24),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                },

                title = {

                    TextField(
                        value = query,
                        onValueChange = onQueryChange,
                        textStyle = AppTypography.bodyLarge,

                        placeholder = {
                            Text(
                                text = stringResource(Res.string.hint_search_list),
                                style = AppTypography.bodyLarge,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        },

                        singleLine = true,

                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),

                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),

                        trailingIcon = {
                            if (query.isNotEmpty()) {
                                IconButton(
                                    onClick = onClear
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_close_search_24),
                                        contentDescription = "Clear",
                                        tint = MaterialTheme.colorScheme.onTertiary
                                    )
                                }
                            }
                        },

                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),

                        keyboardActions = KeyboardActions(
                            onSearch = {
                                keyboardController?.hide()
                                onSearch()
                            }
                        )
                    )
                }
            )
        },

        content = content
    )
}