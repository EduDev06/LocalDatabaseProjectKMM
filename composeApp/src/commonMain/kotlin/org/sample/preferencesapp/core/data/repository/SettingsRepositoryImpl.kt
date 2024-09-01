package org.sample.preferencesapp.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.sample.preferencesapp.core.data.local.PreferenceManager
import org.sample.preferencesapp.core.domain.repository.SettingsRepository

class SettingsRepositoryImpl (
    private val preferences: PreferenceManager
): SettingsRepository {
    override suspend fun saveAppTheme(theme: Int) {
        preferences.setInt(key = PreferenceManager.APP_THEME, value = theme)
    }

    override fun getAppTheme(): Flow<Int?> {
        return preferences.getInt(key = PreferenceManager.APP_THEME)
    }
}