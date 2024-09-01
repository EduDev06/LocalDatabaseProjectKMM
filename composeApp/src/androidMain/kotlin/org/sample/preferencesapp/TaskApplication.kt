package org.sample.preferencesapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.sample.preferencesapp.di.KoinInit

class TaskApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInit().init {
            androidContext(androidContext = this@TaskApplication)
        }
    }
}