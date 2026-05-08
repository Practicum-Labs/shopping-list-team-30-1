package io.dimasla4ee.shoppinglist.core.data.network

/** Используется в `/auth/refresh` эндпоинте. */
data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
) : Response()