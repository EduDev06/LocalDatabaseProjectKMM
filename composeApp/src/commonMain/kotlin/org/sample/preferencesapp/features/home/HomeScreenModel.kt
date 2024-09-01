package org.sample.preferencesapp.features.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.sample.preferencesapp.core.domain.model.Task
import org.sample.preferencesapp.core.domain.repository.TaskRepository

class HomeScreenModel (
    taskRepository: TaskRepository
) : ScreenModel {

    val tasks = taskRepository.getTasks()
        .map { tasks ->
            TaskState.Success(tasks)
        }
        .stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TaskState.Loading
        )
}

sealed class TaskState {
    data object Loading: TaskState()
    data class Success(val tasks: List<Task>): TaskState()
}