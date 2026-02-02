package ca.qc.cstj.premiereapllication.ui.screens.home

import androidx.lifecycle.ViewModel
import ca.qc.cstj.premiereapllication.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private var answer = (Constants.MIN_NUMBER ..Constants.MAX_NUMBER).random()
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    //Actions
    //Plus
    fun increment(){
        if(_uiState.value.number >= Constants.MAX_NUMBER)
            return

        _uiState.update { currentState ->
            currentState.copy(
                number = currentState.number + 1
            )
        }
    }
    //Moin
    fun decrement(){
        if(_uiState.value.number <= Constants.MIN_NUMBER)
            return

        _uiState.update { currentState ->
            currentState.copy(
                number = currentState.number - 1
            )

        }
    }
    ///Essayer
    fun validate(){
        val state = if(uiState.value.number == answer){
            GameState.WIN
        } else if(uiState.value.number < answer){
            GameState.TOO_LOW
        } else {
            GameState.TOO_HIGH
        }

        _uiState.update { currentState
            -> currentState.copy(state = state)
        }
    }

    fun newGame(){
        answer = (Constants.MIN_NUMBER ..Constants.MAX_NUMBER).random()

        _uiState.update {
            GameUiState()
        }
    }

}