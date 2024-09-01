package org.sample.preferencesapp.platform

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.sample.preferecesApp.db.TaskDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = TaskDatabase.Schema, name = "task.db")
    }
}