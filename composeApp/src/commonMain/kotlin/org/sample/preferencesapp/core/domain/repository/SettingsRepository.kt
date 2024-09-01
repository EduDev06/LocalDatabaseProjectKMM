package org.sample.preferencesapp.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun saveAppTheme(theme: Int)
    fun getAppTheme(): Flow<Int?>
}