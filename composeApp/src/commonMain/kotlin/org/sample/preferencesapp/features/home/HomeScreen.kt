package org.sample.preferencesapp.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.sample.preferencesapp.core.domain.model.Task
import org.sample.preferencesapp.core.presentation.component.TaskTab
import preferencesapp.composeapp.generated.resources.Res
import preferencesapp.composeapp.generated.resources.add_task
import preferencesapp.composeapp.generated.resources.ic_waiting

@Composable
fun HomeScreen(
    screenModel: HomeScreenModel = koinInject()
) {
    val tasksState = screenModel.tasks.collectAsState().value
    val tabNavigator = LocalTabNavigator.current
    HomeScreenContent(
        tasksState = tasksState,
        onClickCard = { task ->
            tabNavigator.current = TaskTab.AddTab(
                taskId = task.id,
                goToHome = { tabNavigator.current = TaskTab.HomeTab }
            )
        }
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onClickCard: (Task) -> Unit,
    tasksState: TaskState
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (tasksState) {
            TaskState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is TaskState.Success -> {
                TasksContent(
                    tasks = tasksState.tasks,
                    onClickCard = onClickCard
                )
            }
        }
    }
}

@Composable
private fun TasksContent(
    modifier: Modifier = Modifier,
    onClickCard: (Task) -> Unit,
    tasks: List<Task>
) {
    if (tasks.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize(),
        ) {
            items(tasks) { task ->
                TaskItem(
                    modifier = Modifier.padding(6.dp),
                    onClickCard = onClickCard,
                    task = task
                )
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(300.dp),
                painter = painterResource(Res.drawable.ic_waiting),
                contentDescription = null
            )
            Text(
                text = stringResource(Res.string.add_task)
            )
        }
    }
}

@Composable
private fun TaskItem(
    modifier: Modifier = Modifier,
    onClickCard: (Task) -> Unit,
    task: Task
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(task.color)
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = task.name,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.surface,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = task.description,
                    color = MaterialTheme.colorScheme.surface,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.BottomEnd)
                    .clickable { onClickCard(task) },
                tint = MaterialTheme.colorScheme.surface,
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}