package io.dimasla4ee.shoppinglist.core.presentation.mappers

import junit.framework.TestCase.assertEquals
import org.junit.Test
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.*
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.*

class ShoppingListIconMapperTest {

    @Test
    fun `every ShoppingListIcon maps to correct drawable resource`() {
        assertEquals(Res.drawable.ic_list_alt_24, LIST_ALT.toDrawableResource())
        assertEquals(Res.drawable.ic_list_alt_add_24, LIST_ALT_ADD.toDrawableResource())
        assertEquals(Res.drawable.ic_shopping_bag_24, SHOPPING_BAG.toDrawableResource())
        assertEquals(Res.drawable.ic_shopping_cart_24, SHOPPING_CART.toDrawableResource())
        assertEquals(Res.drawable.ic_cake_24, CAKE.toDrawableResource())
        assertEquals(Res.drawable.ic_celebration_24, CELEBRATION.toDrawableResource())
        assertEquals(Res.drawable.ic_drinks_24, DRINKS.toDrawableResource())
        assertEquals(Res.drawable.ic_picnic_table_24, PICNIC_TABLE.toDrawableResource())
        assertEquals(Res.drawable.ic_present_24, PRESENT.toDrawableResource())
        assertEquals(Res.drawable.ic_baby_stoller_24, BABY_STROLLER.toDrawableResource())
        assertEquals(Res.drawable.ic_bone_24, BONE.toDrawableResource())
        assertEquals(Res.drawable.ic_cat_24, CAT.toDrawableResource())
        assertEquals(Res.drawable.ic_child_24, CHILD.toDrawableResource())
        assertEquals(Res.drawable.ic_infant_24, INFANT.toDrawableResource())
        assertEquals(Res.drawable.ic_paw_24, PAW.toDrawableResource())
        assertEquals(Res.drawable.ic_construction_24, CONSTRUCTION.toDrawableResource())
        assertEquals(Res.drawable.ic_home_24, HOME.toDrawableResource())
        assertEquals(Res.drawable.ic_microwave_24, MICROWAVE.toDrawableResource())
        assertEquals(Res.drawable.ic_dumbell_24, DUMBBELL.toDrawableResource())
        assertEquals(Res.drawable.ic_flower_24, FLOWER.toDrawableResource())
        assertEquals(Res.drawable.ic_gamepad_24, GAMEPAD.toDrawableResource())
        assertEquals(Res.drawable.ic_jigsaw_24, JIGSAW.toDrawableResource())
        assertEquals(Res.drawable.ic_medication_24, MEDICATION.toDrawableResource())
        assertEquals(Res.drawable.ic_meditation_24, MEDITATION.toDrawableResource())
        assertEquals(Res.drawable.ic_palette_24, PALETTE.toDrawableResource())
        assertEquals(Res.drawable.ic_photo_camera_24, PHOTO_CAMERA.toDrawableResource())
        assertEquals(Res.drawable.ic_self_care_24, SELF_CARE.toDrawableResource())
        assertEquals(Res.drawable.ic_skateboard_24, SKATEBOARD.toDrawableResource())
        assertEquals(Res.drawable.ic_snowboard_24, SNOWBOARD.toDrawableResource())
        assertEquals(Res.drawable.ic_walk_24, WALK.toDrawableResource())
        assertEquals(Res.drawable.ic_credit_card_24, CREDIT_CARD.toDrawableResource())
        assertEquals(Res.drawable.ic_luggage_24, LUGGAGE.toDrawableResource())
        assertEquals(Res.drawable.ic_shirt_24, SHIRT.toDrawableResource())
        assertEquals(Res.drawable.ic_wallet_24, WALLET.toDrawableResource())
        assertEquals(Res.drawable.ic_car_24, CAR.toDrawableResource())
        assertEquals(Res.drawable.ic_devices_24, DEVICES.toDrawableResource())
        assertEquals(Res.drawable.ic_docs_add_24, DOCS_ADD.toDrawableResource())
        assertEquals(Res.drawable.ic_graduate_24, GRADUATE.toDrawableResource())
        assertEquals(Res.drawable.ic_import_contacts_24, IMPORT_CONTACTS.toDrawableResource())
        assertEquals(Res.drawable.ic_library_add_check_24, LIBRARY_ADD_CHECK.toDrawableResource())
        assertEquals(Res.drawable.ic_add_circle_24, ADD_CIRCLE.toDrawableResource())
        assertEquals(Res.drawable.ic_cancel_circle_24, CANCEL_CIRCLE.toDrawableResource())
        assertEquals(Res.drawable.ic_clear_24, CLEAR.toDrawableResource())
        assertEquals(Res.drawable.ic_copy_24, COPY.toDrawableResource())
        assertEquals(Res.drawable.ic_delete_24, DELETE.toDrawableResource())
        assertEquals(Res.drawable.ic_drag_handle_24, DRAG_HANDLE.toDrawableResource())
        assertEquals(Res.drawable.ic_drag_pan_24, DRAG_PAN.toDrawableResource())
        assertEquals(Res.drawable.ic_edit_24, EDIT.toDrawableResource())
        assertEquals(Res.drawable.ic_logout_24, LOGOUT.toDrawableResource())
        assertEquals(Res.drawable.ic_remove_circle_24, REMOVE_CIRCLE.toDrawableResource())
        assertEquals(Res.drawable.ic_sort_by_alpha_24, SORT_BY_ALPHA.toDrawableResource())
        assertEquals(Res.drawable.ic_swap_vert_24, SWAP_VERT.toDrawableResource())
    }
}