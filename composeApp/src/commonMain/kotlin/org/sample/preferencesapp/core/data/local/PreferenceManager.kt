package org.sample.preferencesapp.core.data.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getIntOrNullFlow
import com.russhwolf.settings.set

class PreferenceManager(
    private val settings: Settings
) {

    private val observableSettings: ObservableSettings by lazy { settings as ObservableSettings }

    fun setInt(key: String, value: Int) {
        observableSettings.set(key = key, value = value)
    }

    @OptIn(ExperimentalSettingsApi::class)
    fun getInt(key: String) = observableSettings.getIntOrNullFlow(key = key)

    companion object {
        const val APP_THEME = "app_theme_key"
    }
}