package io.dimasla4ee.shoppinglist.core.presentation.mappers

import junit.framework.TestCase.assertEquals
import org.junit.Test
import shoppinglist.composeapp.generated.resources.*
import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit.*

class MeasurementUnitMapperTest {

    @Test
    fun `toStringResource maps all units to correct string resources`() {
        assertEquals(Res.string.liter, LITER.toStringResource())
        assertEquals(Res.string.milliliter, MILLILITER.toStringResource())
        assertEquals(Res.string.packaging, PACKAGING.toStringResource())
        assertEquals(Res.string.pack, PACK.toStringResource())
        assertEquals(Res.string.piece, PIECE.toStringResource())
        assertEquals(Res.string.kilogram, KILOGRAM.toStringResource())
        assertEquals(Res.string.gram, GRAM.toStringResource())
        assertEquals(Res.string.milligrams, MILLIGRAMS.toStringResource())
        assertEquals(Res.string.metre, METRE.toStringResource())
        assertEquals(Res.string.centimetre, CENTIMETRE.toStringResource())
        assertEquals(Res.string.millimetre, MILLIMETRE.toStringResource())
        assertEquals(Res.string.inch, INCH.toStringResource())
        assertEquals(Res.string.foot, FOOT.toStringResource())
        assertEquals(Res.string.yard, YARD.toStringResource())
        assertEquals(Res.string.square_meter, SQUARE_METER.toStringResource())
        assertEquals(Res.string.square_centimetre, SQUARE_CENTIMETRE.toStringResource())
        assertEquals(Res.string.square_millimetre, SQUARE_MILLIMETRE.toStringResource())
        assertEquals(Res.string.cubic_meter, CUBIC_METER.toStringResource())
        assertEquals(Res.string.cubic_decimeter, CUBIC_DECIMETER.toStringResource())
        assertEquals(Res.string.cubic_centimetre, CUBIC_CENTIMETRE.toStringResource())
        assertEquals(Res.string.cubic_millimetre, CUBIC_MILLIMETRE.toStringResource())
    }
}