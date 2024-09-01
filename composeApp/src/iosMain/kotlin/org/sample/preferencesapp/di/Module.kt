package org.sample.preferencesapp.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.sample.preferencesapp.platform.DatabaseDriverFactory
import org.sample.preferencesapp.platform.MultiplatformSettingsWrapper

actual fun platformModule(): Module = module {
    singleOf(::DatabaseDriverFactory)
    single { MultiplatformSettingsWrapper().createSettings() }
}