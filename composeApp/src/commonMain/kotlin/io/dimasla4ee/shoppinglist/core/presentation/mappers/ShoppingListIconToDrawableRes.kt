@file:Suppress("WildcardImport", "ForbiddenComment")

package io.dimasla4ee.shoppinglist.core.presentation.mappers

import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.ADD_CIRCLE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.BABY_STROLLER
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.BONE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.CAKE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.CANCEL_CIRCLE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.CAR
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.CAT
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.CELEBRATION
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.CHILD
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.CLEAR
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.CONSTRUCTION
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.COPY
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.CREDIT_CARD
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.DELETE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.DEVICES
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.DOCS_ADD
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.DRAG_HANDLE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.DRAG_PAN
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.DRINKS
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.DUMBBELL
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.EDIT
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.FLOWER
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.GAMEPAD
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.GRADUATE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.HOME
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.IMPORT_CONTACTS
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.INFANT
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.JIGSAW
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.LIBRARY_ADD_CHECK
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.LIST_ALT
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.LIST_ALT_ADD
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.LOGOUT
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.LUGGAGE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.MEDICATION
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.MEDITATION
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.MICROWAVE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.PALETTE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.PAW
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.PHOTO_CAMERA
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.PICNIC_TABLE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.PRESENT
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.REMOVE_CIRCLE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.SELF_CARE
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.SHIRT
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.SHOPPING_BAG
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.SHOPPING_CART
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.SKATEBOARD
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.SNOWBOARD
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.SORT_BY_ALPHA
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.SWAP_VERT
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.WALK
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon.WALLET
import org.jetbrains.compose.resources.DrawableResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_add_circle_24
import shoppinglist.composeapp.generated.resources.ic_baby_stoller_24
import shoppinglist.composeapp.generated.resources.ic_bone_24
import shoppinglist.composeapp.generated.resources.ic_cake_24
import shoppinglist.composeapp.generated.resources.ic_cancel_circle_24
import shoppinglist.composeapp.generated.resources.ic_car_24
import shoppinglist.composeapp.generated.resources.ic_cat_24
import shoppinglist.composeapp.generated.resources.ic_celebration_24
import shoppinglist.composeapp.generated.resources.ic_child_24
import shoppinglist.composeapp.generated.resources.ic_clear_24
import shoppinglist.composeapp.generated.resources.ic_construction_24
import shoppinglist.composeapp.generated.resources.ic_copy_24
import shoppinglist.composeapp.generated.resources.ic_credit_card_24
import shoppinglist.composeapp.generated.resources.ic_delete_24
import shoppinglist.composeapp.generated.resources.ic_devices_24
import shoppinglist.composeapp.generated.resources.ic_docs_add_24
import shoppinglist.composeapp.generated.resources.ic_drag_handle_24
import shoppinglist.composeapp.generated.resources.ic_drag_pan_24
import shoppinglist.composeapp.generated.resources.ic_drinks_24
import shoppinglist.composeapp.generated.resources.ic_dumbell_24
import shoppinglist.composeapp.generated.resources.ic_edit_24
import shoppinglist.composeapp.generated.resources.ic_flower_24
import shoppinglist.composeapp.generated.resources.ic_gamepad_24
import shoppinglist.composeapp.generated.resources.ic_graduate_24
import shoppinglist.composeapp.generated.resources.ic_home_24
import shoppinglist.composeapp.generated.resources.ic_import_contacts_24
import shoppinglist.composeapp.generated.resources.ic_infant_24
import shoppinglist.composeapp.generated.resources.ic_jigsaw_24
import shoppinglist.composeapp.generated.resources.ic_library_add_check_24
import shoppinglist.composeapp.generated.resources.ic_list_alt_24
import shoppinglist.composeapp.generated.resources.ic_list_alt_add_24
import shoppinglist.composeapp.generated.resources.ic_logout_24
import shoppinglist.composeapp.generated.resources.ic_luggage_24
import shoppinglist.composeapp.generated.resources.ic_medication_24
import shoppinglist.composeapp.generated.resources.ic_meditation_24
import shoppinglist.composeapp.generated.resources.ic_microwave_24
import shoppinglist.composeapp.generated.resources.ic_palette_24
import shoppinglist.composeapp.generated.resources.ic_paw_24
import shoppinglist.composeapp.generated.resources.ic_photo_camera_24
import shoppinglist.composeapp.generated.resources.ic_picnic_table_24
import shoppinglist.composeapp.generated.resources.ic_present_24
import shoppinglist.composeapp.generated.resources.ic_remove_circle_24
import shoppinglist.composeapp.generated.resources.ic_self_care_24
import shoppinglist.composeapp.generated.resources.ic_shirt_24
import shoppinglist.composeapp.generated.resources.ic_shopping_bag_24
import shoppinglist.composeapp.generated.resources.ic_shopping_cart_24
import shoppinglist.composeapp.generated.resources.ic_skateboard_24
import shoppinglist.composeapp.generated.resources.ic_snowboard_24
import shoppinglist.composeapp.generated.resources.ic_sort_by_alpha_24
import shoppinglist.composeapp.generated.resources.ic_swap_vert_24
import shoppinglist.composeapp.generated.resources.ic_walk_24
import shoppinglist.composeapp.generated.resources.ic_wallet_24

fun ShoppingListIcon.toDrawableResource(): DrawableResource = when (this) {
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