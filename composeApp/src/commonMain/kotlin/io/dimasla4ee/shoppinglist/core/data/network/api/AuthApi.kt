package io.dimasla4ee.shoppinglist.core.data.network.api

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Header
import de.jensklingenberg.ktorfit.http.POST
import io.dimasla4ee.shoppinglist.core.data.network.dto.Request
import io.dimasla4ee.shoppinglist.core.domain.model.Response

/**
 * API для работы с аутентификацией пользователя.
 *
 * Интерфейс описывает набор эндпоинтов backend-сервиса:
 * - регистрация нового пользователя;
 * - авторизация;
 * - обновление access token по refresh token;
 * - запуск восстановления пароля;
 * - проверка валидности access token.
 */
interface AuthApi {

    companion object {
        /**
         * Базовый префикс для эндпоинтов аутентификации.
         */
        private const val AUTH = "auth"
    }

    /**
     * Регистрирует нового пользователя.
     *
     * Эндпоинт: `POST /auth/registration`
     *
     * @param request тело запроса с email и password нового пользователя.
     * @return данные авторизованного пользователя после успешной регистрации:
     * идентификатор пользователя, access token и refresh token.
     */
    @POST("$AUTH/registration")
    suspend fun registerUser(
        @Body request: Request.RegisterRequest
    ): Response.RegisterResponse

    /**
     * Обновляет access token по refresh token.
     *
     * Эндпоинт: `POST /auth/refresh`
     *
     * @param request тело запроса, содержащее refresh token.
     * @return новая пара токенов: access token и refresh token.
     */
    @POST("$AUTH/refresh")
    suspend fun refresh(
        @Body request: Request.RefreshTokenRequest
    ): Response.RefreshTokenResponse

    /**
     * Запускает процесс восстановления пароля.
     *
     * Эндпоинт: `POST /auth/recovery`
     *
     * @return текстовое сообщение от сервера о результате запуска восстановления.
     */
    @POST("$AUTH/recovery")
    suspend fun recoverPassword(): Response.RecoverPasswordResponse

    /**
     * Выполняет авторизацию пользователя по email и password.
     *
     * Эндпоинт: `POST /auth/login`
     *
     * @param request тело запроса с учетными данными пользователя.
     * @return данные авторизации: идентификатор пользователя,
     * access token и refresh token.
     */
    @POST("$AUTH/login")
    suspend fun login(
        @Body request: Request.UserAuthRequest
    ): Response.UserAuthResponse

    /**
     * Проверяет валидность access token.
     *
     * Эндпоинт: `GET /auth/check`
     *
     * В заголовок [authorization] необходимо передавать токен.
     *
     * @param authorization значение HTTP-заголовка `Authorization`.
     * @return результат проверки токена.
     */
    @GET("$AUTH/check")
    suspend fun checkUser(
        @Header("Authorization") authorization: String
    ): Response.CheckResponse
}