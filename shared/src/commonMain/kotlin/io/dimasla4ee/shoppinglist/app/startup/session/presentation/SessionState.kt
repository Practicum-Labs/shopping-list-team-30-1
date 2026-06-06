package io.dimasla4ee.shoppinglist.app.startup.session.presentation

import io.dimasla4ee.shoppinglist.core.mvi.MviState

data class SessionState(
    val isLoading: Boolean = false,
    val isAuthorized: Boolean = false,
    val accessToken: String? = null,
    val refreshToken: String? = null
) : MviState