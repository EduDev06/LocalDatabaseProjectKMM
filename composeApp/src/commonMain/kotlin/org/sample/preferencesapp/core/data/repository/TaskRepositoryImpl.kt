package org.sample.preferencesapp.core.data.repository

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.sample.preferecesApp.db.TaskDatabase
import org.sample.preferencesapp.core.data.mapper.toTask
import org.sample.preferencesapp.core.data.mapper.toTaskEntity
import org.sample.preferencesapp.core.data.utils.mapToList
import org.sample.preferencesapp.core.data.utils.mapToOneOrNull
import org.sample.preferencesapp.core.domain.model.Task
import org.sample.preferencesapp.core.domain.repository.TaskRepository

class TaskRepositoryImpl (
    taskDatabase: TaskDatabase
): TaskRepository {

    private val dbQuery = taskDatabase.taskQueries

    override fun getTasks(): Flow<List<Task>> {
        return dbQuery
            .selectAllTasks()
            .asFlow()
            .mapToList()
            .map { taskEntity ->
                taskEntity.map {
                    it.toTask()
                }
            }
    }

    override fun getTaskById(id: Int): Flow<Task?> {
        return dbQuery
            .getTaskById(id)
            .asFlow()
            .mapToOneOrNull()
            .map { taskEntity ->
                taskEntity?.toTask()
            }
    }

    override suspend fun addTask(task: Task) {
        task.toTaskEntity().let { taskEntity ->
            dbQuery.addTasks(
                name = taskEntity.name,
                type = taskEntity.type,
                description = taskEntity.description,
                color = taskEntity.color
            )
        }
    }

    override suspend fun updateTask(task: Task) {
        task.toTaskEntity().let { taskEntity ->
            dbQuery.updateTask(
                id = taskEntity.id,
                name = taskEntity.name,
                type = taskEntity.type,
                description = taskEntity.description,
                color = taskEntity.color
            )
        }
    }

    override suspend fun deleteTask(id: Int) {
        dbQuery.deleteTaskById(id)
    }
}