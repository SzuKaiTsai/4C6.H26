package ca.qc.cstj.inkify.ui.screens.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application): AndroidViewModel(application){

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<SettingsEvent>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    //TODO: Repository

    init {
        //TODO:
    }

    private fun onColorChange(hexColor: String) {
        //TODO:
    }

    private fun onSaveName(newName: String) {
        //TODO:
    }

    private fun save() {
        //TODO:
    }

    private fun onResetSettings() {
        //TODO:
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