package org.sample.preferencesapp.core.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab

@Composable
fun FilledIcon(item: Tab) = when (item.options.index) {
    (0u).toUShort() -> rememberVectorPainter(Icons.Filled.Home)
    else -> rememberVectorPainter(Icons.Filled.Settings)
}