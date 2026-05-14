package io.dimasla4ee.shoppinglist.feature.products_screen.domain.model

import org.jetbrains.compose.resources.StringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.centimetre
import shoppinglist.composeapp.generated.resources.cubic_centimetre
import shoppinglist.composeapp.generated.resources.cubic_decimeter
import shoppinglist.composeapp.generated.resources.cubic_meter
import shoppinglist.composeapp.generated.resources.cubic_millimetre
import shoppinglist.composeapp.generated.resources.foot
import shoppinglist.composeapp.generated.resources.gram
import shoppinglist.composeapp.generated.resources.inch
import shoppinglist.composeapp.generated.resources.kilogram
import shoppinglist.composeapp.generated.resources.liter
import shoppinglist.composeapp.generated.resources.metre
import shoppinglist.composeapp.generated.resources.milligrams
import shoppinglist.composeapp.generated.resources.milliliter
import shoppinglist.composeapp.generated.resources.millimetre
import shoppinglist.composeapp.generated.resources.pack
import shoppinglist.composeapp.generated.resources.packaging
import shoppinglist.composeapp.generated.resources.piece
import shoppinglist.composeapp.generated.resources.square_centimetre
import shoppinglist.composeapp.generated.resources.square_meter
import shoppinglist.composeapp.generated.resources.square_millimetre
import shoppinglist.composeapp.generated.resources.yard

enum class UnitType(
    val titleRes: StringResource
) {

    LITER(Res.string.liter),
    MILLILITER(Res.string.milliliter),

    PACKAGING(Res.string.packaging),
    PACK(Res.string.pack),

    PIECE(Res.string.piece),

    KILOGRAM(Res.string.kilogram),
    GRAM(Res.string.gram),
    MILLIGRAM(Res.string.milligrams),

    METRE(Res.string.metre),
    CENTIMETRE(Res.string.centimetre),
    MILLIMETRE(Res.string.millimetre),

    INCH(Res.string.inch),
    FOOT(Res.string.foot),
    YARD(Res.string.yard),

    SQUARE_METER(Res.string.square_meter),
    SQUARE_CENTIMETRE(Res.string.square_centimetre),
    SQUARE_MILLIMETRE(Res.string.square_millimetre),

    CUBIC_METER(Res.string.cubic_meter),
    CUBIC_DECIMETER(Res.string.cubic_decimeter),
    CUBIC_CENTIMETRE(Res.string.cubic_centimetre),
    CUBIC_MILLIMETRE(Res.string.cubic_millimetre)
}