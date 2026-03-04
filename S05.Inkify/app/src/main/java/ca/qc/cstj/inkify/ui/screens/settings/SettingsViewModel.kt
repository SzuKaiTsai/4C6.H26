package ca.qc.cstj.inkify.ui.screens.settings

import android.app.Application
import androidx.datastore.preferences.core.preferencesOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.inkify.InkifyApplication
import ca.qc.cstj.inkify.data.repositories.SettingRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application): AndroidViewModel(application){

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<SettingsEvent>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    //Repository
    private val app = application.applicationContext as InkifyApplication
    private val settingRepository = SettingRepository(app.dataStore)

    init {
        // called everytime settings change
        // remplacé par le code non commenté
//        settingRepository.preferences.onEach { preferences->
//            _uiState.update {
//                it.copy(settings = preferences)
//            }
//        }.launchIn(viewModelScope)

        settingRepository.jsonPreferences.onEach { preferences->
            _uiState.update {
                it.copy(settings = preferences)
            }
        }.launchIn(viewModelScope)
    }

    private fun onColorChange(hexColor: String) {
        _uiState.update {
            val settings = _uiState.value.settings
            it.copy(settings = settings.copy(noteDefaultColor = hexColor))
        }

        save()
    }

    private fun onSaveName(newName: String) {
        _uiState.update {
            val settings = _uiState.value.settings
            it.copy(settings = settings.copy(name = newName))
        }

        save()
    }

    private fun save() {
        try {

            viewModelScope.launch {
                settingRepository.save(_uiState.value.settings)
            }

        } catch (_: Exception) {

        }
    }

    private fun onResetSettings() {
        viewModelScope.launch {
            settingRepository.reset()
        }
    }

    private fun sendEventToUi(event: SettingsEvent) {
        viewModelScope.launch {
            _events.send(event)
        }
    }

    fun onAction(action: SettingsAction) {
        when(action) {
            is SettingsAction.OnColorChange -> onColorChange(action.hexColor)
            is SettingsAction.OnSaveName -> onSaveName(action.name)
            SettingsAction.OnResetSettings -> onResetSettings()
        }
    }

}