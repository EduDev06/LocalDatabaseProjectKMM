package org.sample.preferencesapp.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.sample.preferencesapp.platform.DatabaseDriverFactory
import org.sample.preferencesapp.platform.MultiplatformSettingsWrapper

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(context = get()) }
    single { MultiplatformSettingsWrapper(context = get()).createSettings() }
}