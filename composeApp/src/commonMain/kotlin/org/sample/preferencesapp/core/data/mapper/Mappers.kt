package org.sample.preferencesapp.core.data.mapper

import org.sample.preferecesApp.db.TaskEntity
import org.sample.preferencesapp.core.domain.model.Task

fun TaskEntity.toTask() = Task(
    id = id,
    name = name,
    description = description,
    type = type,
    color = color
)

fun Task.toTaskEntity() = TaskEntity(
    id = id,
    name = name,
    type = type,
    description = description,
    color = color
)