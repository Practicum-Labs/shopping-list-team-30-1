package io.dimasla4ee.shoppinglist.app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(

    val fabContainer: Color,
    val fabIcon: Color,

    val textFieldText: Color,
    val textFieldHint: Color,
    val textFieldBorder: Color,

    val icon: Color,

    val buttonAddDialogContainer: Color,
    val buttonAddDialogText: Color,

    val buttonDeleteContainer: Color,
    val buttonDeleteText: Color,

    val buttonCancelContainer: Color,
    val buttonCancelText: Color,

    val bottomSheetBackground: Color,
    val bottomSheetHandle: Color,

    val addDialogBackground: Color,
    val dropdownBackground: Color,

    val accent: Color
)

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No colors provided")
}

val LightAppColors = AppColors(

    fabContainer = Beige500,
    fabIcon = Brown500,

    textFieldText = Brown700,
    textFieldHint = Brown400,
    textFieldBorder = Brown300,

    icon = Brown400,

    buttonAddDialogContainer = Beige300,
    buttonAddDialogText = Brown300,

    buttonDeleteContainer = Brown300,
    buttonDeleteText = White,

    buttonCancelContainer = Beige400,
    buttonCancelText = Brown600,

    bottomSheetBackground = Gray300,
    bottomSheetHandle = Gray500,

    addDialogBackground = Beige300,
    dropdownBackground = Beige200,

    accent = Green500
)

val DarkAppColors = AppColors(

    fabContainer = Brown200,
    fabIcon = Black,

    textFieldText = White,
    textFieldHint = Gray300,
    textFieldBorder = Brown100,

    icon = Gray300,

    buttonAddDialogContainer = Brown550,
    buttonAddDialogText = Brown100,

    buttonDeleteContainer = Orange500,
    buttonDeleteText = Brown900,

    buttonCancelContainer = Brown100,
    buttonCancelText = Black,

    bottomSheetBackground = Brown700,
    bottomSheetHandle = Gray400,

    addDialogBackground = Brown550,
    dropdownBackground = Gray600,

    accent = Green500
)