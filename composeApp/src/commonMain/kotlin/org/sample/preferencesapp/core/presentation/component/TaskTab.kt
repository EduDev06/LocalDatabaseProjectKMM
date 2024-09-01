package org.sample.preferencesapp.core.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.sample.preferencesapp.features.add.AddTaskScreen
import org.sample.preferencesapp.features.home.HomeScreen
import org.sample.preferencesapp.features.settings.SettingScreen

internal sealed class TaskTab {
    internal object HomeTab: Tab {
        @Composable
        override fun Content() {
            HomeScreen()
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = "Home"
                val icon = rememberVectorPainter(Icons.Outlined.Home)
                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon
                    )
                }
            }
    }

    internal data class AddTab(
        val taskId: Int? = null,
        val goToHome: () -> Unit = { }
    ): Tab {
        @Composable
        override fun Content() {
            AddTaskScreen (
                taskId = taskId,
                goToHome = goToHome
            )
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = "Add Task"
                val icon = rememberVectorPainter(Icons.Outlined.AddCircle)

                return remember {
                    TabOptions(
                        index = 1u,
                        title = title,
                        icon = icon
                    )
                }
            }
    }

    internal object SettingsTab: Tab {
        @Composable
        override fun Content() {
            SettingScreen()
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = "Settings"
                val icon = rememberVectorPainter(Icons.Outlined.Settings)

                return remember {
                    TabOptions(
                        index = 2u,
                        title = title,
                        icon = icon
                    )
                }
            }
    }
}