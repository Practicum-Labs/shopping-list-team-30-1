package io.dimasla4ee.shoppinglist.core.data.network.dto

import kotlinx.serialization.Serializable

/** Используется в `/auth/login` эндпоинте. */
@Serializable
data class UserAuthResponse(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String
): Response()