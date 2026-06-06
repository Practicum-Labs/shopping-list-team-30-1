package io.dimasla4ee.shoppinglist.core.data.repository

import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import java.util.UUID

class MockAuthRepository : AuthRepository {

    override suspend fun register(
        email: String,
        password: String
    ): DomainResult<Response.RegisterResponse, NetworkError> {
        delay(BIG_DELAY_MS)

        val normalizedEmail = email.trim().lowercase()

        if (!isValidEmail(normalizedEmail)) {
            return badRequest("Некорректный email")
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return badRequest("Пароль менее 7 символов")
        }

        if (users.any { it.email == normalizedEmail }) {
            return conflict("Пользователь уже существует")
        }

        val user = MockUser(
            id = nextUserId++,
            email = normalizedEmail,
            password = password
        )
        users += user

        val session = createSession(user.id)
        sessions.removeAll { it.userId == user.id }
        sessions += session

        return DomainResult.Success(
            Response.RegisterResponse(
                accessToken = session.accessToken,
                refreshToken = session.refreshToken,
                userId = user.id
            )
        )
    }

    override suspend fun login(
        email: String,
        password: String
    ): DomainResult<Response.UserAuthResponse, NetworkError> {
        delay(BIG_DELAY_MS)

        val normalizedEmail = email.trim().lowercase()

        if (!isValidEmail(normalizedEmail)) {
            return badRequest("Некорректный email")
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return badRequest("Пароль менее 7 символов")
        }

        val user = users.firstOrNull {
            it.email == normalizedEmail && it.password == password
        } ?: return unauthorized("Неверный email или пароль")

        val session = createSession(user.id)
        sessions.removeAll { it.userId == user.id }
        sessions += session

        return DomainResult.Success(
            Response.UserAuthResponse(
                accessToken = session.accessToken,
                refreshToken = session.refreshToken,
                userId = user.id
            )
        )
    }

    override suspend fun refresh(
        refreshToken: String
    ): DomainResult<Response.RefreshTokenResponse, NetworkError> {
        delay(MEDIUM_DELAY_MS)

        val oldSession = sessions.firstOrNull { it.refreshToken == refreshToken }
            ?: return unauthorized("Некорректный refresh token")

        if (oldSession.refreshExpiresAt < now()) {
            sessions.remove(oldSession)
            return unauthorized("Refresh token истек")
        }

        val newSession = createSession(oldSession.userId)
        sessions.remove(oldSession)
        sessions += newSession

        return DomainResult.Success(
            Response.RefreshTokenResponse(
                accessToken = newSession.accessToken,
                refreshToken = newSession.refreshToken
            )
        )
    }

    override suspend fun recoverPassword(
        email: String
    ): DomainResult<Response.RecoverPasswordResponse, NetworkError> {
        delay(BIG_DELAY_MS)

        val normalizedEmail = email.trim().lowercase()

        if (!isValidEmail(normalizedEmail)) {
            return DomainResult.Error(
                NetworkError.BadRequest("Некорректный email")
            )
        }

        return DomainResult.Success(
            Response.RecoverPasswordResponse(
                message = "Письмо для восстановления пароля отправлено"
            )
        )
    }

    override suspend fun checkToken(
        accessToken: String
    ): DomainResult<Response.CheckResponse, NetworkError> {
        delay(SMALL_DELAY_MS)

        val session = sessions.firstOrNull { it.accessToken == accessToken }
            ?: return DomainResult.Success(
                Response.CheckResponse(
                    success = false,
                    refresh = false
                )
            )

        val accessAlive = session.accessExpiresAt > now()
        val refreshAlive = session.refreshExpiresAt > now()

        return DomainResult.Success(
            Response.CheckResponse(
                success = accessAlive,
                refresh = !accessAlive && refreshAlive
            )
        )
    }

    private fun createSession(userId: Int): MockSession {
        return MockSession(
            userId = userId,
            accessToken = generateToken(),
            refreshToken = generateToken(),
            accessExpiresAt = now() + ACCESS_TOKEN_LIFETIME_MS,
            refreshExpiresAt = now() + REFRESH_TOKEN_LIFETIME_MS
        )
    }

    private fun generateToken(): String {
        return UUID.randomUUID()
            .toString()
            .replace("-", "")
    }

    private fun isValidEmail(email: String): Boolean {
        return EMAIL_REGEX.matches(email)
    }

    private fun now(): Long = System.currentTimeMillis()

    private fun <T> badRequest(message: String): DomainResult<T, NetworkError> {
        return DomainResult.Error(NetworkError.BadRequest(message))
    }

    private fun <T> unauthorized(message: String): DomainResult<T, NetworkError> {
        return DomainResult.Error(NetworkError.Unauthorized(message))
    }

    private fun <T> conflict(message: String): DomainResult<T, NetworkError> {
        return DomainResult.Error(NetworkError.Conflict(message))
    }

    private data class MockUser(
        val id: Int,
        val email: String,
        val password: String
    )

    private data class MockSession(
        val userId: Int,
        val accessToken: String,
        val refreshToken: String,
        val accessExpiresAt: Long,
        val refreshExpiresAt: Long
    )

    companion object {
        private const val BIG_DELAY_MS = 700L
        private const val MEDIUM_DELAY_MS = 400L
        private const val SMALL_DELAY_MS = 250L
        private const val MIN_PASSWORD_LENGTH = 7
        private const val ACCESS_TOKEN_LIFETIME_MS = 60 * 60 * 1000L
        private const val REFRESH_TOKEN_LIFETIME_MS = 7 * 24 * 60 * 60 * 1000L
        private val EMAIL_REGEX = Regex(".+@.+")
        private val users = mutableListOf<MockUser>()
        private val sessions = mutableListOf<MockSession>()
        private var nextUserId = 1
    }
}