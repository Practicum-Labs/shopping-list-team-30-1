package io.dimasla4ee.shoppinglist.app.ui.theme

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.noteworthy_light_0
import shoppinglist.shared.generated.resources.roboto_medium
import shoppinglist.shared.generated.resources.roboto_regular

/**
 * Typography mapping (Figma):
 *
 * titleLarge  - Regular / 24
 * titleMedium - Regular / 22
 *
 * bodyLarge   - Regular / 16
 * bodyMedium  - Regular / 14
 *
 * labelLarge  - Medium / 16
 * labelMedium - Medium / 14
 */

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun appTypography(): Typography {
    val noteworthyFamily = FontFamily(
        Font(Res.font.noteworthy_light_0, FontWeight.Light)
    )

    val robotoFamily = FontFamily(
        Font(Res.font.roboto_regular, FontWeight.Normal),
        Font(Res.font.roboto_medium, FontWeight.Medium)
    )

    return with(MaterialTheme.typography) {
        copy(
            // Заголовки

            // Логотип
            titleLargeEmphasized = TextStyle(
                fontFamily = noteworthyFamily,
                fontWeight = FontWeight.Light,
                fontSize = 40.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp
            ),

            // 24 / 32 / 0
            titleLarge = TextStyle(
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp
            ),

            // 22 / 28 / 0
            titleMedium = TextStyle(
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
            ),

            // Основа

            // 16 / 24 / 0.5
            bodyLarge = TextStyle(
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Thin,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            ),

            // 14 / 20 / 0.25
            bodyMedium = TextStyle(
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp
            ),

            // Метки,кнопки

            // Medium 16 / 24 / 0.15
            labelLarge = TextStyle(
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp
            ),

            // Medium 14 / 20 / 0.1
            labelMedium = TextStyle(
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp
            )
        )
    }
}
