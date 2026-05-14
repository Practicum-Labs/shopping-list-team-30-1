package io.dimasla4ee.shoppinglist.core.data.network.dto

import kotlinx.serialization.Serializable

/** Используется в `/auth/check` эндпоинте. */
@Serializable
data class CheckResponse(
    val success: Boolean,
    val refresh: Boolean
): Response()