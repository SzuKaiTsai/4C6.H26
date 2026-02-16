package ca.qc.cstj.cleanarchitecture.ui.screens.title

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TitleViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(TitleUiState())
    val uiState = _uiState.asStateFlow()

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

    fun onAction(action: TitleAction){
        when(action) {
            is TitleAction.OnUpdateName -> updateName(action.name)
            is TitleAction.OnUpdatePassword -> updatePassword(action.password)
        }
    }
}