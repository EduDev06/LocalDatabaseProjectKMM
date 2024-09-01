package org.sample.preferencesapp.features.add

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sample.preferencesapp.core.domain.model.Task
import org.sample.preferencesapp.core.domain.model.TaskType
import org.sample.preferencesapp.core.domain.model.taskTypes
import org.sample.preferencesapp.core.domain.repository.TaskRepository

class AddTaskScreenModel (
    private val taskRepository: TaskRepository
) : ScreenModel {

    private val _eventsFlow = MutableSharedFlow<AddTaskEvents>()
    val eventsFlow = _eventsFlow.asSharedFlow()

    private val _taskAddedDialog = mutableStateOf(false)
    val taskAddedDialog: State<Boolean> = _taskAddedDialog
    fun updateTaskAddedDialog(value: Boolean) {
        _taskAddedDialog.value = value
    }

    private val _taskUpdatedDialog = mutableStateOf(false)
    val taskUpdatedDialog: State<Boolean> = _taskUpdatedDialog
    fun updateTaskUpdatedDialog(value: Boolean) {
        _taskUpdatedDialog.value = value
    }

    private val _taskRemovedDialog = mutableStateOf(false)
    val taskRemovedDialog: State<Boolean> = _taskRemovedDialog
    fun updateTaskRemovedDialog(value: Boolean) {
        _taskRemovedDialog.value = value
    }

    private val _taskName = mutableStateOf("")
    val taskName: State<String> = _taskName
    fun setTaskName(value: String) {
        _taskName.value = value
    }

    private val _taskDescription = mutableStateOf("")
    val taskDescription: State<String> = _taskDescription
    fun setTaskDescription(value: String) {
        _taskDescription.value = value
    }

    private val _selectedOption = mutableStateOf(taskTypes.last())
    val selectedOption = _selectedOption
    fun setSelectedOption(option: TaskType) {
        _selectedOption.value = option
    }

    fun saveTask() {
        screenModelScope.launch {
            try {
                taskRepository.addTask(
                    Task(
                        name = _taskName.value,
                        description = _taskDescription.value,
                        type = _selectedOption.value.name,
                        color = _selectedOption.value.color
                    )
                )
                updateTaskAddedDialog(true)
            } catch (e: Exception) {
                _eventsFlow.emit(AddTaskEvents.ShowSnackBar("There was an error"))
            }
        }
    }

    private val _task = mutableStateOf<Task?>(null)
    val task = _task
    fun getTask(taskId: Int?) {
        screenModelScope.launch {
            taskId?.let { id ->
                taskRepository.getTaskById(id).collect { task ->
                    task?.let {
                        _task.value = it
                        fillDataTask(it)
                    }
                }
            } ?: reset()
        }
    }

    fun updateTask(id: Int) {
        screenModelScope.launch {
            try {
                taskRepository.updateTask(
                    Task(
                        id = id,
                        name = _taskName.value,
                        type = _selectedOption.value.name,
                        description = _taskDescription.value,
                        color = _selectedOption.value.color
                    )
                )
                updateTaskUpdatedDialog(true)
            } catch (e: Exception) {
                _eventsFlow.emit(AddTaskEvents.ShowSnackBar("There was an error"))
            }
        }
    }

    fun deleteTask(taskId: Int) {
        screenModelScope.launch {
            try {
                taskRepository.deleteTask(taskId)
                updateTaskRemovedDialog(true)
            } catch (e: Exception) {
                _eventsFlow.emit(AddTaskEvents.ShowSnackBar("There was an error"))
            }
        }
    }


    private fun fillDataTask(task: Task) {
        fun getSelectedOption(type: String): TaskType {
            return taskTypes.firstOrNull { taskType ->
                taskType.name == type
            } ?: taskTypes.last()
        }

        setTaskName(task.name)
        setTaskDescription(task.description)
        setSelectedOption(getSelectedOption(task.type))
    }


    private fun reset() {
        setTaskName("")
        setTaskDescription("")
        setSelectedOption(taskTypes.last())
    }
}