package io.dimasla4ee.shoppinglist.core.data.network.dto

import kotlinx.serialization.Serializable

/**
 * Базовый класс сетевых ответов.
 *
 * @param resultCode HTTP-код ответа.
 */
@Serializable
open class Response {
    var resultCode = 0
}