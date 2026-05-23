package io.dimasla4ee.shoppinglist.core.data.network.client

import io.dimasla4ee.shoppinglist.core.data.network.api.AuthApi
import io.dimasla4ee.shoppinglist.core.data.network.dto.Request
import io.dimasla4ee.shoppinglist.core.domain.model.NetworkError
import io.dimasla4ee.shoppinglist.core.domain.model.Response
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class KtorfitNetworkClient(
    private val api: AuthApi
) : NetworkClient {

    override suspend fun <T : Response> doRequest(request: Request): Result<T> = runCatching {
        val responseData: Response = when (request) {
            is Request.RefreshTokenRequest -> api.refresh(request)
            is Request.RegisterRequest -> api.registerUser(request)
            is Request.UserAuthRequest -> api.login(request)
            is Request.CheckUserRequest -> api.checkUser(request.authorization)
            is Request.RecoverPasswordRequest -> api.recoverPassword(request.email)
        }

        @Suppress("UNCHECKED_CAST")
        responseData as T
    }
}

/** Преобразует [Throwable] из Ktor/Ktorfit в доменную ошибку [NetworkError]. */
suspend fun Throwable.toNetworkError(): NetworkError = when (this) {
    is ClientRequestException -> {
        val body = response.bodyAsText()
        when (response.status) {
            HttpStatusCode.BadRequest -> NetworkError.BadRequest(body)
            HttpStatusCode.Unauthorized -> NetworkError.Unauthorized(body)
            HttpStatusCode.Conflict -> NetworkError.Conflict(body)
            else -> NetworkError.Unknown(this)
        }
    }

    is ServerResponseException -> {
        val body = response.bodyAsText()
        NetworkError.ServerError(body)
    }

    is UnknownHostException,
    is ConnectException,
    is SocketTimeoutException -> {
        NetworkError.NoConnection(this)
    }

    else -> NetworkError.Unknown(this)
}
