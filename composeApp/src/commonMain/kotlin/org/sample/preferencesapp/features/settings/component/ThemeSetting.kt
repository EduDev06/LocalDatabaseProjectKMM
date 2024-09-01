package org.sample.preferencesapp.features.settings.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import preferencesapp.composeapp.generated.resources.Res
import preferencesapp.composeapp.generated.resources.subtitle_theme_setting
import preferencesapp.composeapp.generated.resources.theme_setting

@Composable
fun ThemeSetting(
    isExpanded: (String) -> Boolean,
    onExpand: (String) -> Unit,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit
) {
    val darkThemeValue = if (darkTheme) "Dark" else "Light"
    SettingCard(
        onExpand = { onExpand("Theme") },
        icon = Icons.Filled.LightMode,
        title = stringResource(Res.string.theme_setting),
        isExpanded = isExpanded("Theme")
    ) {
        AutoStartSession(
            title = stringResource(Res.string.subtitle_theme_setting, darkThemeValue),
            checked = darkTheme,
            onCheckedChange = {
                onDarkThemeChange(it)
            }
        )
    }
}

@Composable
fun SettingCard(
    modifier: Modifier = Modifier,
    onExpand: () -> Unit,
    title: String,
    icon: ImageVector,
    isExpanded: Boolean,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onExpand() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                IconButton(onClick = { onExpand() }) {
                    Icon(
                        imageVector = if (isExpanded) {
                            Icons.Filled.KeyboardArrowUp
                        } else {
                            Icons.Filled.KeyboardArrowDown
                        },
                        contentDescription = null
                    )
                }
            }

            AnimatedVisibility(isExpanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    content()
                }
            }
        }
    }
}

@Composable
fun AutoStartSession(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}