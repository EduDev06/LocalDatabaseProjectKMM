package org.sample.preferencesapp.core.presentation.component

import preferencesapp.composeapp.generated.resources.Res
import preferencesapp.composeapp.generated.resources.go_to_home
import preferencesapp.composeapp.generated.resources.task_removed
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

private const val DIALOG_BUILD_TIME: Long = 300

@Composable
fun CustomTransitionDialog(
    onDismissRequest: () -> Unit,
    content: @Composable (AnimatedTransitionDialogHelper) -> Unit
) {
    val onDismissSharedFlow: MutableSharedFlow<Any> = remember { MutableSharedFlow() }
    val coroutineScope = rememberCoroutineScope()
    val animateTrigger = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        launch {
            delay(DIALOG_BUILD_TIME)
            animateTrigger.value = true
        }

        launch {
            onDismissSharedFlow.asSharedFlow().collectLatest {
                startDismissWithExitAnimation(animateTrigger, onDismissRequest)
            }
        }
    }

    Dialog(
        onDismissRequest = {
            coroutineScope.launch {
                startDismissWithExitAnimation(animateTrigger, onDismissRequest)
            }
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedScaleInTransition(visible = animateTrigger.value) {
                content(AnimatedTransitionDialogHelper(coroutineScope, onDismissSharedFlow))
            }
        }
    }
}

@Composable
fun BoxScope.TaskAddedDialog(
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: () -> Unit
) {
    CustomTransitionDialog(onDismissRequest = { onDismiss() }) { dialogHelper ->
        Surface(
            modifier = modifier
                .height(290.dp)
                .width(350.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(16.dp)
        ) {
            Card {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(110.dp),
                        imageVector = Icons.Filled.CheckCircle,
                        tint = Color.Green.copy(
                            alpha = 0.7F
                        ),
                        contentDescription = null
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = text,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = {
                            dialogHelper::triggerAnimatedDismiss.invoke()
                        },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        )
                    ) {
                        Text(
                            text = stringResource(Res.string.go_to_home),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BoxScope.TaskRemovedDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    CustomTransitionDialog(onDismissRequest = { onDismiss() }) { dialogHelper ->
        Surface(
            modifier = modifier
                .height(290.dp)
                .width(350.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(16.dp)
        ) {
            Card {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(110.dp),
                        imageVector = Icons.Filled.Delete,
                        tint = MaterialTheme.colorScheme.error.copy(
                            alpha = 0.7F
                        ),
                        contentDescription = null
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = stringResource(Res.string.task_removed),
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = {
                            dialogHelper::triggerAnimatedDismiss.invoke()
                        },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        )
                    ) {
                        Text(
                            text = stringResource(Res.string.go_to_home),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}