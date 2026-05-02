@file:Suppress("WildcardImport", "ForbiddenComment")

package io.dimasla4ee.shoppinglist.core.presentation.mappers

import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.*
import org.jetbrains.compose.resources.DrawableResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.*

fun ShoppingListIcon.toDrawableResource(): DrawableResource = when(this) {
    LIST_ALT -> Res.drawable.ic_list_alt_24
    LIST_ALT_ADD -> Res.drawable.ic_list_alt_add_24
    SHOPPING_BAG -> Res.drawable.ic_shopping_bag_24
    SHOPPING_CART -> Res.drawable.ic_shopping_cart_24
    CAKE -> Res.drawable.ic_cake_24
    CELEBRATION -> Res.drawable.ic_celebration_24
    DRINKS -> Res.drawable.ic_drinks_24
    PICNIC_TABLE -> Res.drawable.ic_picnic_table_24
    PRESENT -> Res.drawable.ic_present_24
    BABY_STROLLER -> Res.drawable.ic_baby_stoller_24    // TODO: В res опечатка (stoller)
    BONE -> Res.drawable.ic_bone_24
    CAT -> Res.drawable.ic_cat_24
    CHILD -> Res.drawable.ic_child_24
    INFANT -> Res.drawable.ic_infant_24
    PAW -> Res.drawable.ic_paw_24
    CONSTRUCTION -> Res.drawable.ic_construction_24
    HOME -> Res.drawable.ic_home_24
    MICROWAVE -> Res.drawable.ic_microwave_24
    DUMBBELL -> Res.drawable.ic_dumbell_24              // TODO: В res опечатка (dumbell)
    FLOWER -> Res.drawable.ic_flower_24
    GAMEPAD -> Res.drawable.ic_gamepad_24
    JIGSAW -> Res.drawable.ic_jigsaw_24
    MEDICATION -> Res.drawable.ic_medication_24
    MEDITATION -> Res.drawable.ic_meditation_24
    PALETTE -> Res.drawable.ic_palette_24
    PHOTO_CAMERA -> Res.drawable.ic_photo_camera_24
    SELF_CARE -> Res.drawable.ic_self_care_24
    SKATEBOARD -> Res.drawable.ic_skateboard_24
    SNOWBOARD -> Res.drawable.ic_snowboard_24
    WALK -> Res.drawable.ic_walk_24
    CREDIT_CARD -> Res.drawable.ic_credit_card_24
    LUGGAGE -> Res.drawable.ic_luggage_24
    SHIRT -> Res.drawable.ic_shirt_24
    WALLET -> Res.drawable.ic_wallet_24
    CAR -> Res.drawable.ic_car_24
    DEVICES -> Res.drawable.ic_devices_24
    DOCS_ADD -> Res.drawable.ic_docs_add_24
    GRADUATE -> Res.drawable.ic_graduate_24
    IMPORT_CONTACTS -> Res.drawable.ic_import_contacts_24
    LIBRARY_ADD_CHECK -> Res.drawable.ic_library_add_check_24
    ADD_CIRCLE -> Res.drawable.ic_add_circle_24
    CANCEL_CIRCLE -> Res.drawable.ic_cancel_circle_24
    CLEAR -> Res.drawable.ic_clear_24
    COPY -> Res.drawable.ic_copy_24
    DELETE -> Res.drawable.ic_delete_24
    DRAG_HANDLE -> Res.drawable.ic_drag_handle_24
    DRAG_PAN -> Res.drawable.ic_drag_pan_24
    EDIT -> Res.drawable.ic_edit_24
    LOGOUT -> Res.drawable.ic_logout_24
    REMOVE_CIRCLE -> Res.drawable.ic_remove_circle_24
    SORT_BY_ALPHA -> Res.drawable.ic_sort_by_alpha_24
    SWAP_VERT -> Res.drawable.ic_swap_vert_24
}