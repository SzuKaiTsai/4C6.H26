package ca.qc.cstj.cleanarchitecture.ui.screens.title

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TitleViewModel: ViewModel() {

    // Actions = Screen vers ViewModel
    // UiState = ViewModel vers Screen
    private val _uiState = MutableStateFlow(TitleUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<TitleUiEvent>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private fun updateName(name: String){
        _uiState.update {
            it.copy(name = name)
        }
    }

    private fun updatePassword(password: String){
        _uiState.update {
            it.copy(password = password)
        }
    }

    private fun togglePasswordVisibility(){
        _uiState.update{
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    private fun sendUiEvent(event: TitleUiEvent){
        viewModelScope.launch {
            _events.send(event)
        }
    }

    private fun login(){

        var isError = false

        if(_uiState.value.password != "123456"|| _uiState.value.name.isBlank()){
            isError = true
            sendUiEvent(TitleUiEvent.OnLoginError)
        }

        else{
            sendUiEvent(TitleUiEvent.OnLoginSuccess)
        }

        _uiState.update {
            it.copy(isError = isError)
        }
    }

    fun onAction(action: TitleAction){
        when(action) {
            is TitleAction.OnUpdateName -> updateName(action.name)
            is TitleAction.OnUpdatePassword -> updatePassword(action.password)
            TitleAction.OnTogglePasswordVisibility -> togglePasswordVisibility()
            TitleAction.OnLoginClicked -> login()
        }
    }

}