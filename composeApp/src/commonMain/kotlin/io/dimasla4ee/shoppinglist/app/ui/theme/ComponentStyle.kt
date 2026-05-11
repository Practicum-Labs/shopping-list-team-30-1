@file:OptIn(ExperimentalMaterial3Api::class)

package io.dimasla4ee.shoppinglist.app.ui.theme

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun addDialogButtons() = ButtonDefaults.buttonColors(
    containerColor = LocalAppColors.current.buttonAddDialogContainer,
    contentColor = LocalAppColors.current.buttonAddDialogText
)

@Composable
fun deleteDialogButtonDelete() = ButtonDefaults.buttonColors(
    containerColor = LocalAppColors.current.buttonDeleteContainer,
    contentColor = LocalAppColors.current.buttonDeleteText
)

@Composable
fun deleteDialogButtonCancel() = ButtonDefaults.buttonColors(
    containerColor = LocalAppColors.current.buttonCancelContainer,
    contentColor = LocalAppColors.current.buttonCancelText
)

@Composable
fun defaultDialogButtonColors() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.tertiary,
    contentColor = MaterialTheme.colorScheme.secondary
)

@Composable
fun dialogTextFieldColors() = TextFieldDefaults.colors(

    // фон
    focusedContainerColor = MaterialTheme.colorScheme.tertiary,
    unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,

    // курсор
    cursorColor = MaterialTheme.colorScheme.secondary,

    // бордер
    focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
    unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
    // текст
    focusedTextColor = MaterialTheme.colorScheme.onSecondary
)

@Composable
fun appTopBarColors() = TopAppBarDefaults.topAppBarColors(
    containerColor = Color.Transparent
)

@Composable
fun appRadioButtonColors() = RadioButtonDefaults.colors(
    selectedColor = MaterialTheme.colorScheme.error,
    unselectedColor = MaterialTheme.colorScheme.onTertiary
)