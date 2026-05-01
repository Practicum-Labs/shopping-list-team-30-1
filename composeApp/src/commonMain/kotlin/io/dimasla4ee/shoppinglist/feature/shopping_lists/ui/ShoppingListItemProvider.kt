package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListItem
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_baby_stoller_24
import shoppinglist.composeapp.generated.resources.ic_cake_24
import shoppinglist.composeapp.generated.resources.ic_shopping_cart_24

class ShoppingListItemProvider : PreviewParameterProvider<ShoppingListItem> {

    override val values: Sequence<ShoppingListItem>
        get() = sequenceOf(
            ShoppingListItem(
                1,
                "Аб",
                Res.drawable.ic_baby_stoller_24
            ),
            ShoppingListItem(
                2,
                "Продукты",
                Res.drawable.ic_shopping_cart_24
            ),
            ShoppingListItem(
                3,
                """
                    Очень длинный текст, который точно не поместится в одну строку карточки.
                    Даже в две строчки не поместится.
                    А вот в три-четыре наверняка поместится.
                    """.trimIndent(),
                Res.drawable.ic_cake_24
            )
        )

}