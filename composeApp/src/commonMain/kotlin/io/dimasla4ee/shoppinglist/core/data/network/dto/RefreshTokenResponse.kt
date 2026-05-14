package io.dimasla4ee.shoppinglist.core.data.network.dto

import kotlinx.serialization.Serializable

/** Используется в `/auth/refresh` эндпоинте. */
@Serializable
data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
) : Response()