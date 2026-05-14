package io.dimasla4ee.shoppinglist.core.data.network.dto

import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class DtoSerializationTest {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    // ==================== Request serialization ====================

    @Test
    fun `RegisterRequest serializes correctly`() {
        // given
        val request = Request.RegisterRequest(
            email = "test@mail.com",
            password = "123456"
        )

        // when
        val encoded = json.encodeToString(Request.RegisterRequest.serializer(), request)
        val decoded = json.decodeFromString(Request.RegisterRequest.serializer(), encoded)

        // then
        assertEquals(request, decoded)
    }

    @Test
    fun `RefreshTokenRequest serializes correctly`() {
        // given
        val request = Request.RefreshTokenRequest(refreshToken = "my_refresh_token")

        // when
        val encoded = json.encodeToString(Request.RefreshTokenRequest.serializer(), request)
        val decoded = json.decodeFromString(Request.RefreshTokenRequest.serializer(), encoded)

        // then
        assertEquals(request, decoded)
    }

    @Test
    fun `UserAuthRequest serializes correctly`() {
        // given
        val request = Request.UserAuthRequest(
            email = "user@mail.com",
            password = "pass"
        )

        // when
        val encoded = json.encodeToString(Request.UserAuthRequest.serializer(), request)
        val decoded = json.decodeFromString(Request.UserAuthRequest.serializer(), encoded)

        // then
        assertEquals(request, decoded)
    }

    @Test
    fun `CheckUserRequest serializes correctly`() {
        // given
        val request = Request.CheckUserRequest(authorization = "Bearer token123")

        // when
        val encoded = json.encodeToString(Request.CheckUserRequest.serializer(), request)
        val decoded = json.decodeFromString(Request.CheckUserRequest.serializer(), encoded)

        // then
        assertEquals(request, decoded)
    }

    // ==================== Response deserialization ====================

    @Test
    fun `RegisterResponse deserializes correctly`() {
        // given
        val jsonString = """
            {
                "userId": 1,
                "accessToken": "access_123",
                "refreshToken": "refresh_123"
            }
        """.trimIndent()

        // when
        val response = json.decodeFromString(
            Response.RegisterResponse.serializer(),
            jsonString
        )

        // then
        assertEquals(1, response.userId)
        assertEquals("access_123", response.accessToken)
        assertEquals("refresh_123", response.refreshToken)
    }

    @Test
    fun `UserAuthResponse deserializes correctly`() {
        // given
        val jsonString = """
            {
                "userId": 42,
                "accessToken": "auth_access",
                "refreshToken": "auth_refresh"
            }
        """.trimIndent()

        // when
        val response = json.decodeFromString(
            Response.UserAuthResponse.serializer(),
            jsonString
        )

        // then
        assertEquals(42, response.userId)
        assertEquals("auth_access", response.accessToken)
        assertEquals("auth_refresh", response.refreshToken)
    }

    @Test
    fun `RefreshTokenResponse deserializes correctly`() {
        // given
        val jsonString = """
            {
                "accessToken": "new_access",
                "refreshToken": "new_refresh"
            }
        """.trimIndent()

        // when
        val response = json.decodeFromString(
            Response.RefreshTokenResponse.serializer(),
            jsonString
        )

        // then
        assertEquals("new_access", response.accessToken)
        assertEquals("new_refresh", response.refreshToken)
    }

    @Test
    fun `CheckResponse deserializes correctly`() {
        // given
        val jsonString = """
            {
                "success": true,
                "refresh": false
            }
        """.trimIndent()

        // when
        val response = json.decodeFromString(
            Response.CheckResponse.serializer(),
            jsonString
        )

        // then
        assertEquals(true, response.success)
        assertEquals(false, response.refresh)
    }

    @Test
    fun `RegisterResponse ignores unknown keys from server`() {
        // given — сервер может присылать дублирующие поля (user_id и userId)
        val jsonString = """
            {
                "user_id": 1,
                "userId": 1,
                "access_token": "at",
                "accessToken": "at",
                "refresh_token": "rt",
                "refreshToken": "rt"
            }
        """.trimIndent()

        // when
        val response = json.decodeFromString(
            Response.RegisterResponse.serializer(),
            jsonString
        )

        // then — парсится без ошибок благодаря ignoreUnknownKeys = true
        assertEquals(1, response.userId)
        assertEquals("at", response.accessToken)
        assertEquals("rt", response.refreshToken)
    }
}