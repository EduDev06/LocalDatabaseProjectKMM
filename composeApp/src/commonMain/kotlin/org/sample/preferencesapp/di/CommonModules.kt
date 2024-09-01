package org.sample.preferencesapp.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.sample.preferecesApp.db.TaskDatabase
import org.sample.preferecesApp.db.TaskEntity
import org.sample.preferencesapp.core.data.adapter.colorAdapter
import org.sample.preferencesapp.core.data.adapter.idAdapter
import org.sample.preferencesapp.core.data.local.PreferenceManager
import org.sample.preferencesapp.core.data.repository.SettingsRepositoryImpl
import org.sample.preferencesapp.core.data.repository.TaskRepositoryImpl
import org.sample.preferencesapp.core.domain.repository.SettingsRepository
import org.sample.preferencesapp.core.domain.repository.TaskRepository
import org.sample.preferencesapp.features.add.AddTaskScreenModel
import org.sample.preferencesapp.features.home.HomeScreenModel
import org.sample.preferencesapp.features.settings.SettingsScreenModel
import org.sample.preferencesapp.main.MainViewModel
import org.sample.preferencesapp.platform.DatabaseDriverFactory

fun commonModules() = module {
    /**
     * Database
     */

    single<TaskDatabase> {
        TaskDatabase(
            driver = get<DatabaseDriverFactory>().createDriver(),
            taskEntityAdapter = TaskEntity.Adapter(
                idAdapter = idAdapter,
                colorAdapter = colorAdapter
            )
        )
    }

    /**
     * Repositories
     */

    single<TaskRepository> {
        TaskRepositoryImpl(
            taskDatabase = get()
        )
    }

    single<SettingsRepository> {
        SettingsRepositoryImpl(
            preferences = get()
        )
    }

    /**
     * Multiplatform-Settings
     */

    single<PreferenceManager> {
        PreferenceManager(
            settings = get()
        )
    }

    /**
     *  ViewModel
     */
    single<HomeScreenModel> {
        HomeScreenModel(
            taskRepository = get()
        )
    }

    single<AddTaskScreenModel> {
        AddTaskScreenModel(
            taskRepository = get()
        )
    }

    single<SettingsScreenModel> {
        SettingsScreenModel(
            repository = get()
        )
    }

    single<MainViewModel> {
        MainViewModel(
            settingsRepository = get()
        )
    }
}


expect fun platformModule(): Module