package io.dimasla4ee.shoppinglist.app.startup.session.presentation

import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface SessionIntent : MviIntent {
    data object LoadSession : SessionIntent
    data object Logout : SessionIntent
    data object RefreshSession : SessionIntent
}