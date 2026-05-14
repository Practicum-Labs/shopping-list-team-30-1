package io.dimasla4ee.shoppinglist.core.data.network.client

import io.dimasla4ee.shoppinglist.core.data.network.dto.Request
import io.dimasla4ee.shoppinglist.core.data.network.dto.Response

interface NetworkClient {
    suspend fun <T: Response> doRequest(request: Request): Result<T>
}