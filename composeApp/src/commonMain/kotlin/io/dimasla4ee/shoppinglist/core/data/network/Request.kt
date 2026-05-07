package io.dimasla4ee.shoppinglist.core.data.network

/**
 * Базовый интерфейс для запросов к серверу.
 *
 * [Документация к API](https://practicumopbackend-production.up.railway.app/swagger-ui/index.html)
 * */
sealed interface Request {
    /** Используется в `/auth/registration` эндпоинте. */
    data class RegisterRequest(val email: String, val password: String) : Request

    /** Используется в `/auth/refresh` эндпоинте. */
    data class RefreshTokenRequest(val refreshToken: String) : Request

    /** Используется в `/auth/login` эндпоинте. */
    data class UserAuthRequest(val email: String, val password: String) : Request
}