package io.dimasla4ee.shoppinglist.core.domain.model

import kotlinx.serialization.Serializable

/**
 * Базовый интерфейс сетевых ответов.
 *
 * [Документация к API](https://practicumopbackend-production.up.railway.app/swagger-ui/index.html)
 */
@Serializable
sealed interface Response {
    /** Используется в `/auth/registration` эндпоинте. */
    @Serializable
    data class RegisterResponse(
        val userId: Int,
        val accessToken: String,
        val refreshToken: String
    ) : Response

    /** Используется в `/auth/login` эндпоинте. */
    @Serializable
    data class UserAuthResponse(
        val userId: Int,
        val accessToken: String,
        val refreshToken: String
    ) : Response

    /** Используется в `/auth/recovery` эндпоинте. */
    @Serializable
    data class RecoverPasswordResponse(
        val message: String
    ) : Response

    /** Используется в `/auth/check` эндпоинте. */
    @Serializable
    data class CheckResponse(
        val success: Boolean,
        val refresh: Boolean
    ) : Response

    /** Используется в `/auth/refresh` эндпоинте. */
    @Serializable
    data class RefreshTokenResponse(
        val accessToken: String,
        val refreshToken: String
    ) : Response
}