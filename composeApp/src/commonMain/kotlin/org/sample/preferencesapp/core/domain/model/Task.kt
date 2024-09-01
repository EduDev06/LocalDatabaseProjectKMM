package org.sample.preferencesapp.core.domain.model

data class Task(
    val id: Int = 0,
    val name: String,
    val type: String,
    val description: String,
    val color: Long
)