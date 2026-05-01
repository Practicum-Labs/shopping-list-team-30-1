package io.dimasla4ee.shoppinglist.app.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(

    val fabContainer: Color,
    val fabIcon: Color,

    val textFieldText: Color,
    val textFieldHint: Color,
    val textFieldBorder: Color,

    val icon: Color,

    val buttonDeleteContainer: Color,
    val buttonDeleteText: Color,

    val buttonCancelContainer: Color,
    val buttonCancelText: Color,

    val bottomSheetBackground: Color,
    val bottomSheetHandle: Color,

    val searchBackground: Color,
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

    buttonDeleteContainer = Brown300,
    buttonDeleteText = White,

    buttonCancelContainer = Beige400,
    buttonCancelText = Brown600,

    bottomSheetBackground = Gray300,
    bottomSheetHandle = Gray500,

    searchBackground = Beige300,
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

    buttonDeleteContainer = Orange500,
    buttonDeleteText = Brown900,

    buttonCancelContainer = Brown100,
    buttonCancelText = Black,

    bottomSheetBackground = Brown700,
    bottomSheetHandle = Gray400,

    searchBackground = Brown800,
    dropdownBackground = Gray600,

    accent = Green500
)