package io.dimasla4ee.shoppinglist.app.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

object AppDimensions {

    val paddingLarge = 44.dp
    val paddingVeryBig = 32.dp
    val paddingBigger = 24.dp
    val paddingBig = 18.dp
    val bottomSheetIconsSpaceBy = 12.dp
    val paddingMedium = 16.dp
    val paddingSmall = 8.dp
    val paddingVerySmall = 6.dp
    val endPaddingTopBar = 4.dp

    val clickableAreaOfIcon = 56.dp
    val areaOfIcon = 40.dp

    val spacerSmall = 8.dp
    val spacerLarge = 48.dp
    val spacerVeryLarge = 94.dp

    val fab = DpSize(56.dp, 56.dp)
    val topAppIconSize = DpSize(48.dp, 48.dp)

    object TopAppBar {
        val paddings = PaddingValues(
            top = paddingBig,
            bottom = paddingBig,
            start = paddingMedium
        )
    }

    object DialogAddition {
        val iconSize = DpSize(24.dp, 24.dp)
        val spaceIconTextField = 24.dp
        val textFieldSize = DpSize(264.dp, 56.dp)
        val textFieldPadding = PaddingValues(
            top = paddingMedium,
            bottom = paddingMedium,
            start = paddingMedium
        )
        val cornerRadius = 28.dp
    }

    object DialogAdditionButton {
        val contentPadding = PaddingValues(vertical = 12.dp, horizontal = 10.dp)
    }

    object ShoppingListItem {
        val itemSize = DpSize(380.dp, 56.dp)
        val iconSize = DpSize(40.dp, 40.dp)
        val shoppingListItemPadding = PaddingValues(
            top = paddingSmall,
            bottom = paddingSmall,
            start = paddingSmall
        )
        val cornerRadius = 12.dp
    }

    object DialogDeletion {
        val iconSize = DpSize(24.dp, 24.dp)
        val cornerRadius = 28.dp
    }

    object DialogDeletionButton {
        val contentPadding = PaddingValues(vertical = 24.dp, horizontal = 10.dp)
        val cornerRadius = 100.dp
    }

    object ShoppingFoundListItem {
        val iconSize = DpSize(40.dp, 40.dp)
        val shoppingListItemPadding = PaddingValues(
            top = paddingMedium,
            bottom = paddingMedium,
            start = paddingSmall
        )
    }

    object AddNewProductNameTextField {
        val textFieldSize = DpSize(368.dp, 64.dp)
        val textFieldPadding = PaddingValues(
            top = paddingMedium,
            bottom = paddingMedium,
            start = paddingMedium
        )
    }

    object AddNewProductAmountTextField {
        val textFieldSize = DpSize(120.dp, 56.dp)
        val textFieldPadding = PaddingValues(
            top = paddingMedium,
            bottom = paddingMedium,
            start = paddingMedium
        )
    }

    object AddNewProductUnitTextField {
        val textFieldSize = DpSize(120.dp, 56.dp)
        val textFieldPadding = PaddingValues(
            top = paddingMedium,
            bottom = paddingMedium,
            start = paddingMedium
        )
    }

    object ProductItem {
        val iconSize = DpSize(24.dp, 24.dp)
        val shoppingListItemPadding = PaddingValues(
            top = paddingSmall,
            bottom = paddingSmall,
            start = paddingMedium
        )
    }

    object ProductBottomSheetItem {
        val iconSize = DpSize(24.dp, 24.dp)
        val shoppingListItemPadding = PaddingValues(
            top = paddingMedium,
            bottom = paddingMedium,
            start = paddingMedium
        )
    }

    object IconBottomSheet {
        val handler = DpSize(32.dp, 4.dp)
    }

    object RegisterDimensions {
        val ContentSpacing = 16.dp
        val ButtonHeight = 56.dp
        val ButtonCornerRadius = 8.dp
        val FooterTopPadding = 16.dp
        val ScreenHorizontalPadding = 16.dp
    }

    object SignInDimensions {
        val ContentSpacing = 16.dp
        val ButtonHeight = 56.dp
        val ButtonCornerRadius = 8.dp
        val FooterTopPadding = 16.dp
        val ScreenHorizontalPadding = 16.dp
    }
}