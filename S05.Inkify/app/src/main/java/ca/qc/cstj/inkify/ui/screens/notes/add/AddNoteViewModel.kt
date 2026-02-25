package ca.qc.cstj.inkify.ui.screens.notes.add

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.inkify.InkifyApplication
import ca.qc.cstj.inkify.data.repositories.NoteRepository
import ca.qc.cstj.inkify.models.Note
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(AddNoteUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<AddNoteEvent>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    //TODO: Repositories
    private val app = application.applicationContext as InkifyApplication
    private val noteRepository = NoteRepository(app.database.noteDao())
    init {
        //TODO:
    }

    private fun save() {
        val note = _uiState.value.newNote
        if(!note.isValid()){
            return
        }
        // .launch demarre un thread (async)
        viewModelScope.launch {
            try {
                noteRepository.create(note)
                _uiState.update {
                    it.copy(newNote = Note())
                }
            }
            catch (ex: Exception){

            }
        }

    }

    private fun updateNoteTitle(newTitle: String) {
        _uiState.update {
            val currentNote = _uiState.value.newNote
            it.copy(newNote = currentNote.copy(title= newTitle))
        }
    }

    private fun updateNoteContent(newText: String) {
        _uiState.update {
            val currentNote = _uiState.value.newNote
            it.copy(newNote = currentNote.copy(content = newText))
        }
    }

    private fun updateColor(newColor: Color) {
        _uiState.update {
            val currentNote = _uiState.value.newNote
            it.copy(newNote = currentNote.copy(color = newColor))
        }
    }

    fun onAction(action: AddNoteAction) {
       when(action){
            is AddNoteAction.OnUpdateColor -> updateColor(action.color)
           is AddNoteAction.OnUpdateContent -> updateNoteContent(action.content)
           is AddNoteAction.OnUpdateTitle -> updateNoteTitle(action.title)
           AddNoteAction.OnSaveClicked -> save()
       }

    }
}