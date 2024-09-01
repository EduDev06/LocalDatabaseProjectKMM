package org.sample.preferencesapp.features.add

import preferencesapp.composeapp.generated.resources.Res
import preferencesapp.composeapp.generated.resources.delete_task
import preferencesapp.composeapp.generated.resources.update_task
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.sample.preferencesapp.core.domain.model.TaskType
import org.sample.preferencesapp.core.domain.model.taskTypes
import org.sample.preferencesapp.core.presentation.component.AppDropDown
import org.sample.preferencesapp.core.presentation.component.AppInputTextField
import org.sample.preferencesapp.core.presentation.component.TaskAddedDialog
import org.sample.preferencesapp.core.presentation.component.TaskRemovedDialog
import preferencesapp.composeapp.generated.resources.save_task
import preferencesapp.composeapp.generated.resources.task_added
import preferencesapp.composeapp.generated.resources.task_description
import preferencesapp.composeapp.generated.resources.task_description_placeholder
import preferencesapp.composeapp.generated.resources.task_name
import preferencesapp.composeapp.generated.resources.task_name_placeholder
import preferencesapp.composeapp.generated.resources.task_type
import preferencesapp.composeapp.generated.resources.task_updated

@Composable
fun AddTaskScreen(
    taskId: Int? = null,
    screenModel: AddTaskScreenModel = koinInject(),
    goToHome: () -> Unit
) {
    val taskName = screenModel.taskName.value
    val taskDescription = screenModel.taskDescription.value
    val selectedTaskType = screenModel.selectedOption.value
    val snackbarHostState = remember { SnackbarHostState() }
    val taskAddedDialog = screenModel.taskAddedDialog.value
    val taskRemovedDialog = screenModel.taskRemovedDialog.value
    val taskUpdatedDialog = screenModel.taskUpdatedDialog.value

    LaunchedEffect(key1 = true) {
        screenModel.getTask(taskId)
        withContext(Dispatchers.Main.immediate) {
            screenModel.eventsFlow.collect { event ->
                when (event) {
                    is AddTaskEvents.ShowSnackBar -> {
                        snackbarHostState.showSnackbar(
                            message = event.message
                        )
                    }

                    AddTaskEvents.GoToHome -> goToHome()
                }
            }
        }
    }

    AddTaskScreenContent(
        snackbarHostState = snackbarHostState,
        taskId = taskId,
        taskName = taskName,
        onTaskNameChange = screenModel::setTaskName,
        taskAddedDialogState = taskAddedDialog,
        taskRemovedDialogState = taskRemovedDialog,
        taskUpdatedDialogState = taskUpdatedDialog,
        taskDescription = taskDescription,
        selectedTaskType = selectedTaskType,
        goToHome = goToHome,
        updateTaskAdded = screenModel::updateTaskAddedDialog,
        updateTaskRemoved = screenModel::updateTaskRemovedDialog,
        updateTaskUpdated = screenModel::updateTaskUpdatedDialog,
        onTaskDescriptionChange = screenModel::setTaskDescription,
        onSelectedTaskTypeChange = screenModel::setSelectedOption,
        onClickSaveTask = screenModel::saveTask,
        onClickUpdateTask = screenModel::updateTask,
        onClickDeleteTask = screenModel::deleteTask,
        taskOptions = taskTypes
    )
}

@Composable
private fun AddTaskScreenContent(
    snackbarHostState: SnackbarHostState,
    taskId: Int?,
    taskAddedDialogState: Boolean,
    taskRemovedDialogState: Boolean,
    taskUpdatedDialogState: Boolean,
    modifier: Modifier = Modifier,
    taskName: String,
    taskOptions: List<TaskType>,
    taskDescription: String,
    selectedTaskType: TaskType,
    goToHome: () -> Unit,
    updateTaskAdded: (Boolean) -> Unit,
    updateTaskRemoved: (Boolean) -> Unit,
    updateTaskUpdated: (Boolean) -> Unit,
    onTaskDescriptionChange: (String) -> Unit,
    onTaskNameChange: (String) -> Unit,
    onClickSaveTask: () -> Unit,
    onClickUpdateTask: (Int) -> Unit,
    onClickDeleteTask: (Int) -> Unit,
    onSelectedTaskTypeChange: (TaskType) -> Unit,
) {
    Scaffold(
        snackbarHost = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                SnackbarHost(
                    hostState = snackbarHostState,
                    snackbar = { snackBarData ->
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .height(50.dp)
                                .clickable {
                                    snackbarHostState.currentSnackbarData?.dismiss()
                                },
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = snackBarData.visuals.message,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                )
                                Icon(
                                    modifier = Modifier.size(34.dp),
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AppInputTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = taskName,
                    label = {
                        Text(
                            text = stringResource(Res.string.task_name),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    placeHolder = {
                        Text(
                            text = stringResource(Res.string.task_name_placeholder)
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words
                    ),
                    onValueChange = onTaskNameChange
                )

                AppInputTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = taskDescription,
                    label = {
                        Text(
                            text = stringResource(Res.string.task_description),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    placeHolder = {
                        Text(
                            text = stringResource(Res.string.task_description_placeholder)
                        )
                    },
                    maxLines = 4,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    onValueChange = onTaskDescriptionChange
                )

                AppDropDown(
                    label = {
                        Text(
                            text = stringResource(Res.string.task_type),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    options = taskOptions,
                    selectedOption = selectedTaskType.name,
                    onOptionSelected = onSelectedTaskTypeChange
                )
            }

            if (taskId != null) {
                OptionButtons(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    taskId = taskId,
                    onClickUpdateTask = { onClickUpdateTask(it) },
                    onClickDeleteTask = { onClickDeleteTask(it) }
                )
            } else {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    onClick = { onClickSaveTask() }
                ) {
                    Text(
                        text = stringResource(Res.string.save_task),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            if (taskAddedDialogState) {
                TaskAddedDialog(text = stringResource(Res.string.task_added)) {
                    updateTaskAdded(false)
                    goToHome()
                }
            }

            if (taskRemovedDialogState) {
                TaskRemovedDialog {
                    updateTaskRemoved(false)
                    goToHome()
                }
            }

            if (taskUpdatedDialogState) {
                TaskAddedDialog(text = stringResource(Res.string.task_updated)) {
                    updateTaskUpdated(false)
                    goToHome()
                }
            }
        }
    }
}

@Composable
fun OptionButtons(
    modifier: Modifier = Modifier,
    taskId: Int,
    onClickUpdateTask: (Int) -> Unit,
    onClickDeleteTask: (Int) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Button(
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            onClick = { onClickUpdateTask(taskId) }
        ) {
            Icon(
                imageVector = Icons.Default.Update,
                contentDescription = null
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = stringResource(Res.string.update_task),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Button(
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            onClick = { onClickDeleteTask(taskId) }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = stringResource(Res.string.delete_task),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}