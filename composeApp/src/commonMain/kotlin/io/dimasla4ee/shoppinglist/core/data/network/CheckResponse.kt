package io.dimasla4ee.shoppinglist.core.data.network

/** Используется в `/auth/check` эндпоинте. */
data class CheckResponse(
    val success: Boolean,
    val refresh: Boolean
): Response()