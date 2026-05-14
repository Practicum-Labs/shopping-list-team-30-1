package io.dimasla4ee.shoppinglist.core.data.network.api

import io.dimasla4ee.shoppinglist.core.data.network.dto.Request
import io.dimasla4ee.shoppinglist.core.data.network.dto.Response

/**
 * Фейковая реализация [AuthApi] для unit-тестов.
 *
 * Позволяет задавать ожидаемые ответы и отслеживать,
 * какие методы были вызваны.
 */
class FakeAuthApi : AuthApi {
    var registerResponse = Response.RegisterResponse(
        userId = 1,
        accessToken = "access_token_stub",
        refreshToken = "refresh_token_stub"
    )

    var loginResponse = Response.UserAuthResponse(
        userId = 1,
        accessToken = "access_token_stub",
        refreshToken = "refresh_token_stub"
    )

    var refreshResponse = Response.RefreshTokenResponse(
        accessToken = "new_access_token",
        refreshToken = "new_refresh_token"
    )

    var checkResponse = Response.CheckResponse(
        success = true,
        refresh = false
    )

    var recoverResponse = Response.RecoverPasswordResponse(
        message = "Recovery email sent"
    )

    var registerCalled = false
        private set
    var loginCalled = false
        private set
    var refreshCalled = false
        private set
    var checkCalled = false
        private set
    var recoverCalled = false
        private set

    var lastRegisterRequest: Request.RegisterRequest? = null
        private set
    var lastLoginRequest: Request.UserAuthRequest? = null
        private set
    var lastRefreshRequest: Request.RefreshTokenRequest? = null
        private set
    var lastCheckAuthorization: String? = null
        private set

    var shouldThrow: Throwable? = null

    override suspend fun registerUser(
        request: Request.RegisterRequest
    ): Response.RegisterResponse {
        shouldThrow?.let { throw it }
        registerCalled = true
        lastRegisterRequest = request
        return registerResponse
    }

    override suspend fun refresh(
        request: Request.RefreshTokenRequest
    ): Response.RefreshTokenResponse {
        shouldThrow?.let { throw it }
        refreshCalled = true
        lastRefreshRequest = request
        return refreshResponse
    }

    override suspend fun recoverPassword(): Response.RecoverPasswordResponse {
        shouldThrow?.let { throw it }
        recoverCalled = true
        return recoverResponse
    }

    override suspend fun login(
        request: Request.UserAuthRequest
    ): Response.UserAuthResponse {
        shouldThrow?.let { throw it }
        loginCalled = true
        lastLoginRequest = request
        return loginResponse
    }

    override suspend fun checkUser(
        authorization: String
    ): Response.CheckResponse {
        shouldThrow?.let { throw it }
        checkCalled = true
        lastCheckAuthorization = authorization
        return checkResponse
    }

    /**
     * Сбрасывает все флаги и сохранённые аргументы.
     */
    fun reset() {
        registerCalled = false
        loginCalled = false
        refreshCalled = false
        checkCalled = false
        recoverCalled = false
        lastRegisterRequest = null
        lastLoginRequest = null
        lastRefreshRequest = null
        lastCheckAuthorization = null
        shouldThrow = null
    }
}