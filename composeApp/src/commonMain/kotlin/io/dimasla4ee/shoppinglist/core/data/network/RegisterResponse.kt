package io.dimasla4ee.shoppinglist.core.data.network

/** Используется в `/auth/registration` эндпоинте. */
data class RegisterResponse(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String
) : Response()
