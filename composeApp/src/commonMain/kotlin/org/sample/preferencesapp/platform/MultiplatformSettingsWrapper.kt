package org.sample.preferencesapp.platform

import com.russhwolf.settings.Settings

expect class MultiplatformSettingsWrapper {
    fun createSettings(): Settings
}