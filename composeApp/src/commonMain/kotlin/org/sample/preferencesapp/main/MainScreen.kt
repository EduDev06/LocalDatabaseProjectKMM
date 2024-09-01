package org.sample.preferencesapp.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.sample.preferencesapp.core.presentation.component.TaskTab
import org.sample.preferencesapp.core.presentation.utils.FilledIcon

class MainScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(tab = TaskTab.HomeTab) {
            val tabNavigator = LocalTabNavigator.current
            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = MaterialTheme.colorScheme.background
                    ) {
                        TabNavigationItem(TaskTab.HomeTab)
                        TabNavigationItem(TaskTab.SettingsTab)
                    }
                },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        modifier = Modifier
                            .offset(y = 65.dp)
                            .size(42.dp),
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            if (tabNavigator.current is TaskTab.AddTab) return@FloatingActionButton
                            tabNavigator.current = TaskTab.AddTab(goToHome = { tabNavigator.current = TaskTab.HomeTab })
                        },
                        elevation = FloatingActionButtonDefaults.elevation(
                            defaultElevation = 0.dp
                        ),
                        shape = CircleShape
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    CurrentScreen()
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab

    BottomNavigationItem(
        modifier = Modifier.offset(
            x = when (tab.options.index) {
                (0u).toUShort() -> (-24).dp
                (1u).toUShort() -> 24.dp
                else -> 24.dp
            }
        ),
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let { painter ->
                Icon(
                    painter = if (isSelected) {
                        FilledIcon(tab)
                    } else {
                        painter
                    },
                    contentDescription = tab.options.title,
                    tint = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    }
                )
            }
        }
    )
}