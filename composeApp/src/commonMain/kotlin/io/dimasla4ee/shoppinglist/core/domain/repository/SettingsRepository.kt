package io.dimasla4ee.shoppinglist.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val isDarkTheme: Flow<Boolean>

    suspend fun setDarkTheme(enabled: Boolean)
}