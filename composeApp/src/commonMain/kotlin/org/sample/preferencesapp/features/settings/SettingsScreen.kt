package org.sample.preferencesapp.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.sample.preferencesapp.features.settings.component.ThemeSetting
import preferencesapp.composeapp.generated.resources.Res
import preferencesapp.composeapp.generated.resources.settings_title

@Composable
fun SettingScreen(
    screenModel: SettingsScreenModel = koinInject()
) {
    val darkTheme = when (screenModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }

    SettingsContent(
        darkTheme = darkTheme,
        optionsOpened =  screenModel.optionsOpened,
        openOptions = { option ->
            screenModel.openOptions(option)
        },
        onDarkThemeChange = { themeValue ->
            screenModel.setAppTheme(
                appTheme = if (themeValue) 1 else 0
            )
        }
    )
}

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsContent(
    darkTheme: Boolean,
    optionsOpened: List<String>,
    openOptions: (String) -> Unit,
    onDarkThemeChange: (Boolean) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.settings_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ThemeSetting(
                    isExpanded = { title ->
                        optionsOpened.contains(title)
                    },
                    onExpand = { title ->
                        openOptions(title)
                    },
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange
                )
            }
        }
    }
}