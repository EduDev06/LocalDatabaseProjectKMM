package org.sample.preferencesapp.core.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sample.preferencesapp.core.domain.model.Task

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
    fun getTaskById(id: Int): Flow<Task?>
    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(id: Int)
}