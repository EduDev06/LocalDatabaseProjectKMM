package org.sample.preferencesapp.features.settings

import androidx.compose.runtime.mutableStateListOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sample.preferencesapp.core.domain.repository.SettingsRepository

class SettingsScreenModel (
    private val repository: SettingsRepository
): ScreenModel {

    val appTheme: StateFlow<Int?> = repository
        .getAppTheme()
        .map { it }
        .stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

    fun setAppTheme(appTheme: Int) {
        screenModelScope.launch {
            repository.saveAppTheme(appTheme)
        }
    }

    val optionsOpened = mutableStateListOf("")
    fun openOptions(option: String) {
        if (optionsOpened.contains(option)) {
            optionsOpened.remove(option)
        } else {
            optionsOpened.add(option)
        }
    }
}