package io.dimasla4ee.shoppinglist.core.domain.model

/** Доменные ошибки сетевых запросов. */
sealed interface NetworkError {

    /** 400 — некорректный запрос. */
    data class BadRequest(val message: String? = null) : NetworkError

    /** 401 — не авторизован. */
    data class Unauthorized(val message: String? = null) : NetworkError

    /** 409 — конфликт. */
    data class Conflict(val message: String? = null) : NetworkError

    /** 500 — внутренняя ошибка сервера. */
    data class ServerError(val message: String? = null) : NetworkError

    /** Нет интернета или тайм-аут. */
    data class NoConnection(val cause: Throwable? = null) : NetworkError

    /** Непредвиденная ошибка. */
    data class Unknown(val cause: Throwable? = null) : NetworkError
}