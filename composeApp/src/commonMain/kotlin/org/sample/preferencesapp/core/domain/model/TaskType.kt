package org.sample.preferencesapp.core.domain.model

data class TaskType(
    val name: String,
    val color: Long
) {
    override fun toString(): String {
        return name
    }
}

val taskTypes = listOf(
    TaskType(
        name = "Work",
        color = 0xFF3375fd
    ),
    TaskType(
        name = "Study",
        color = 0xFFff686d
    ),
    TaskType(
        name = "Personal",
        color = 0xFF24c469
    ),
    TaskType(
        name = "Other",
        color = 0xFF734efe
    )
)