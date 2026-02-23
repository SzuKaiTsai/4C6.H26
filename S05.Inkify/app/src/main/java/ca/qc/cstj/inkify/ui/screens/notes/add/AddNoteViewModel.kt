package ca.qc.cstj.inkify.ui.screens.notes.add

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(AddNoteUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<AddNoteEvent>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    //TODO: Repositories

    init {
        //TODO:
    }

    private fun save() {
        //TODO:
    }

    private fun updateNoteTitle(newTitle: String) {
        //TODO:
    }

    private fun updateNoteText(newText: String) {
        //TODO:
    }

    private fun updateColor(newColor: Color) {
        //TODO:
    }

    fun onAction(action: AddNoteAction) {
       //TODO:

    }
}