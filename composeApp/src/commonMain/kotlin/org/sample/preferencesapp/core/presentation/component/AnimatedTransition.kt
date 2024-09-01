package org.sample.preferencesapp.core.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay

private const val ANIMATION_TIME: Long = 500

@Composable
fun AnimatedScaleInTransition(
    visible: Boolean,
    animationTime: Int = ANIMATION_TIME.toInt(),
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            animationSpec = tween(animationTime)
        ),
        exit = scaleOut(
            animationSpec = tween(animationTime)
        ),
        content = content
    )
}

suspend fun startDismissWithExitAnimation(
    animateTrigger: MutableState<Boolean>,
    onDismissRequest: () -> Unit
) {
    animateTrigger.value = false
    delay(ANIMATION_TIME)
    onDismissRequest()
}