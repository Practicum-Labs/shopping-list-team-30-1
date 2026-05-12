package io.dimasla4ee.shoppinglist.core.data.network.client

import io.dimasla4ee.shoppinglist.core.data.network.api.AuthApi
import io.dimasla4ee.shoppinglist.core.data.network.dto.Request
import io.dimasla4ee.shoppinglist.core.data.network.dto.Response

class KtorfitNetworkClient(
    private val api: AuthApi
) : NetworkClient {

    override suspend fun <T : Response> doRequest(request: Request): Result<T> = runCatching {
        val responseData: Response = when(request) {
            is Request.RefreshTokenRequest -> api.refresh(request)
            is Request.RegisterRequest -> api.registerUser(request)
            is Request.UserAuthRequest -> api.login(request)
            is Request.CheckUserRequest -> api.checkUser(request.authorization)
            is Request.RecoverPasswordRequest -> api.recoverPassword()
        }

        @Suppress("UNCHECKED_CAST")
        responseData as T
    }
}