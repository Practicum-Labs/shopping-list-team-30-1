package io.dimasla4ee.shoppinglist.core.domain.repository

import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val themeMode: Flow<ThemeMode>

    suspend fun setThemeMode(mode: ThemeMode)
}