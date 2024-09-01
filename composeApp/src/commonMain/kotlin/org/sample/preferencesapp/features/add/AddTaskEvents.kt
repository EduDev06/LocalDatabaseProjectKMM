package org.sample.preferencesapp.features.add

sealed class AddTaskEvents {
    data class ShowSnackBar(val message: String): AddTaskEvents()
    data object GoToHome: AddTaskEvents()
}