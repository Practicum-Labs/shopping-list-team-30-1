package io.dimasla4ee.shoppinglist.core.data.network

/** Используется в `/auth/login` эндпоинте. */
data class UserAuthResponse(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String
): Response()