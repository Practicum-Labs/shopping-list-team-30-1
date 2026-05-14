package io.dimasla4ee.shoppinglist.core.data.network.dto

import kotlinx.serialization.Serializable

/** Используется в `/auth/registration` эндпоинте. */
@Serializable
data class RegisterResponse(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String
) : Response()
