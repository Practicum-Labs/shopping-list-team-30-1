package io.dimasla4ee.shoppinglist.core.data.network.dto

import kotlinx.serialization.Serializable

/**
 * Базовый интерфейс для запросов к серверу.
 *
 * [Документация к API](https://practicumopbackend-production.up.railway.app/swagger-ui/index.html)
 */
@Serializable
sealed interface Request {
    /** Используется в `/auth/registration` эндпоинте. */
    @Serializable
    data class RegisterRequest(val email: String, val password: String) : Request

    /** Используется в `/auth/refresh` эндпоинте. */
    @Serializable
    data class RefreshTokenRequest(val refreshToken: String) : Request

    /** Используется в `/auth/login` эндпоинте. */
    @Serializable
    data class UserAuthRequest(val email: String, val password: String) : Request
}