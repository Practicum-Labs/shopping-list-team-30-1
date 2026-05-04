@file:Suppress("WildcardImport")

package io.dimasla4ee.shoppinglist.core.presentation.mappers

import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit.*
import org.jetbrains.compose.resources.StringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.*

fun MeasurementUnit.toStringResource(): StringResource = when(this) {
    LITER -> Res.string.liter
    MILLILITER -> Res.string.milliliter
    PACKAGING -> Res.string.packaging
    PACK -> Res.string.pack
    PIECE -> Res.string.piece
    KILOGRAM -> Res.string.kilogram
    GRAM -> Res.string.gram
    MILLIGRAMS -> Res.string.milligrams
    METRE -> Res.string.metre
    CENTIMETRE -> Res.string.centimetre
    MILLIMETRE -> Res.string.millimetre
    INCH -> Res.string.inch
    FOOT -> Res.string.foot
    YARD -> Res.string.yard
    SQUARE_METER -> Res.string.square_meter
    SQUARE_CENTIMETRE -> Res.string.square_centimetre
    SQUARE_MILLIMETRE -> Res.string.square_millimetre
    CUBIC_METER -> Res.string.cubic_meter
    CUBIC_DECIMETER -> Res.string.cubic_decimeter
    CUBIC_CENTIMETRE -> Res.string.cubic_centimetre
    CUBIC_MILLIMETRE -> Res.string.cubic_millimetre
}