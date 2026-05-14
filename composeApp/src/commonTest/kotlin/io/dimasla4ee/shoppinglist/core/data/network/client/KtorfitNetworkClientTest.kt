package io.dimasla4ee.shoppinglist.core.data.network.client

import io.dimasla4ee.shoppinglist.core.data.network.api.FakeAuthApi
import io.dimasla4ee.shoppinglist.core.data.network.dto.Request
import io.dimasla4ee.shoppinglist.core.data.network.dto.Response
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KtorfitNetworkClientTest {

    private lateinit var fakeApi: FakeAuthApi
    private lateinit var client: KtorfitNetworkClient

    @Before
    fun setup() {
        fakeApi = FakeAuthApi()
        client = KtorfitNetworkClient(api = fakeApi)
    }

    // ==================== Registration ====================

    @Test
    fun `registerUser - delegates to api and returns RegisterResponse`() = runTest {
        // given
        val request = Request.RegisterRequest(
            email = "test@mail.com",
            password = "123456"
        )
        val expectedResponse = Response.RegisterResponse(
            userId = 42,
            accessToken = "access_42",
            refreshToken = "refresh_42"
        )
        fakeApi.registerResponse = expectedResponse

        // when
        val result = client.doRequest<Response.RegisterResponse>(request)

        // then
        assertTrue(result.isSuccess)
        assertEquals(expectedResponse, result.getOrNull())
        assertTrue(fakeApi.registerCalled)
        assertEquals(request, fakeApi.lastRegisterRequest)
    }

    @Test
    fun `registerUser - passes correct email and password`() = runTest {
        // given
        val request = Request.RegisterRequest(
            email = "user@example.com",
            password = "strongPassword"
        )

        // when
        client.doRequest<Response.RegisterResponse>(request)

        // then
        assertEquals("user@example.com", fakeApi.lastRegisterRequest?.email)
        assertEquals("strongPassword", fakeApi.lastRegisterRequest?.password)
    }

    // ==================== Login ====================

    @Test
    fun `login - delegates to api and returns UserAuthResponse`() = runTest {
        // given
        val request = Request.UserAuthRequest(
            email = "user@mail.com",
            password = "pass"
        )
        val expectedResponse = Response.UserAuthResponse(
            userId = 10,
            accessToken = "login_access",
            refreshToken = "login_refresh"
        )
        fakeApi.loginResponse = expectedResponse

        // when
        val result = client.doRequest<Response.UserAuthResponse>(request)

        // then
        assertTrue(result.isSuccess)
        assertEquals(expectedResponse, result.getOrNull())
        assertTrue(fakeApi.loginCalled)
        assertEquals(request, fakeApi.lastLoginRequest)
    }

    // ==================== Refresh ====================

    @Test
    fun `refresh - delegates to api and returns RefreshTokenResponse`() = runTest {
        // given
        val request = Request.RefreshTokenRequest(refreshToken = "old_refresh")
        val expectedResponse = Response.RefreshTokenResponse(
            accessToken = "refreshed_access",
            refreshToken = "refreshed_refresh"
        )
        fakeApi.refreshResponse = expectedResponse

        // when
        val result = client.doRequest<Response.RefreshTokenResponse>(request)

        // then
        assertTrue(result.isSuccess)
        assertEquals(expectedResponse, result.getOrNull())
        assertTrue(fakeApi.refreshCalled)
        assertEquals(request, fakeApi.lastRefreshRequest)
    }

    // ==================== Check ====================

    @Test
    fun `checkUser - delegates to api with authorization header`() = runTest {
        // given
        val token = "my_access_token"
        val request = Request.CheckUserRequest(authorization = "Bearer $token")
        val expectedResponse = Response.CheckResponse(
            success = true,
            refresh = false
        )
        fakeApi.checkResponse = expectedResponse

        // when
        val result = client.doRequest<Response.CheckResponse>(request)

        // then
        assertTrue(result.isSuccess)
        assertEquals(expectedResponse, result.getOrNull())
        assertTrue(fakeApi.checkCalled)
        assertEquals("Bearer $token", fakeApi.lastCheckAuthorization)
    }

    @Test
    fun `checkUser - returns refresh true when token needs refresh`() = runTest {
        // given
        val request = Request.CheckUserRequest(authorization = "Bearer expired_token")
        fakeApi.checkResponse = Response.CheckResponse(
            success = true,
            refresh = true
        )

        // when
        val result = client.doRequest<Response.CheckResponse>(request)

        // then
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()!!.refresh)
    }

    // ==================== Recover ====================

    @Test
    fun `recoverPassword - delegates to api and returns RecoverPasswordResponse`() = runTest {
        // given
        val request = Request.RecoverPasswordRequest
        val expectedResponse = Response.RecoverPasswordResponse(
            message = "Check your email"
        )
        fakeApi.recoverResponse = expectedResponse

        // when
        val result = client.doRequest<Response.RecoverPasswordResponse>(request)

        // then
        assertTrue(result.isSuccess)
        assertEquals(expectedResponse, result.getOrNull())
        assertTrue(fakeApi.recoverCalled)
    }

    // ==================== Error handling ====================

    @Test
    fun `doRequest - returns failure when api throws exception`() = runTest {
        // given
        val request = Request.RegisterRequest(
            email = "test@mail.com",
            password = "123"
        )
        val expectedException = RuntimeException("Network error")
        fakeApi.shouldThrow = expectedException

        // when
        val result = client.doRequest<Response.RegisterResponse>(request)

        // then
        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
    }

    @Test
    fun `doRequest - returns failure for all request types when api throws`() = runTest {
        // given
        fakeApi.shouldThrow = RuntimeException("Server down")

        val requests = listOf(
            Request.RegisterRequest("a@b.com", "pass"),
            Request.UserAuthRequest("a@b.com", "pass"),
            Request.RefreshTokenRequest("token"),
            Request.CheckUserRequest("Bearer token"),
            Request.RecoverPasswordRequest
        )

        // when & then
        requests.forEach { request ->
            val result = client.doRequest<Response>(request)
            assertTrue(result.isFailure, "Expected failure for ${request::class.simpleName}")
        }
    }

    // ==================== Isolation ====================

    @Test
    fun `doRequest - only calls the corresponding api method`() = runTest {
        // given
        val request = Request.RegisterRequest("a@b.com", "pass")

        // when
        client.doRequest<Response.RegisterResponse>(request)

        // then — только register вызван, остальные нет
        assertTrue(fakeApi.registerCalled)
        assertTrue(!fakeApi.loginCalled)
        assertTrue(!fakeApi.refreshCalled)
        assertTrue(!fakeApi.checkCalled)
        assertTrue(!fakeApi.recoverCalled)
    }
}