package io.dimasla4ee.shoppinglist.app.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppDimensions {

    val paddingVeryLarge = 64.dp
    val paddingLarge = 44.dp
    val paddingExtraBig = 32.dp
    val paddingVeryBig = 24.dp
    val paddingBigPlus = 22.dp
    val paddingBig = 18.dp
    val paddingMedium = 16.dp
    val paddingSmallPlus = 10.dp
    val paddingSmall = 8.dp
    val paddingVerySmall = 6.dp
    val paddingExtraSmall = 4.dp

    val bottomSheetIconsSpaceBy = 12.dp

    val logoSize = 78.sp
    val logoOffset = 2.dp

    val spacerSmall = 8.dp
    val spacerMedium = 16.dp
    val spacerBig = 32.dp
    val spacerVeryBig = 48.dp
    val spacerLarge = 94.dp
    val spacerVeryLarge = 120.dp

    val clickableAreaOfIcon = 56.dp
    val areaOfIcon = 40.dp

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

    object BottomSheet {
        val topCornerRadius = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        val handlerSize = DpSize(32.dp, 4.dp)
        val handlerCornerRadius = 2.dp
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