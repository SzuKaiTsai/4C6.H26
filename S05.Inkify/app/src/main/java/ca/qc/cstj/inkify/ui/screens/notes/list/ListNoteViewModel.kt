package ca.qc.cstj.inkify.ui.screens.notes.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.inkify.InkifyApplication
import ca.qc.cstj.inkify.data.repositories.NoteRepository
import ca.qc.cstj.inkify.models.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListNoteViewModel (application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(ListNoteUiState())
    val uiState = _uiState.asStateFlow()

    //TODO: Repository
    private val app = application.applicationContext as InkifyApplication
    private val noteRepository = NoteRepository(app.database.noteDao())

    init {
       viewModelScope.launch {
           noteRepository.retrieveAll().collect { notes ->
               _uiState.update {
                   it.copy(notes=notes)
               }
           }
       }
    }

    fun delete(note: Note) {
        //TODO:
    }

    fun save(note: Note) {
        //TODO:
    }

    fun onAction(action: ListNoteAction) {
        when(action) {
            is ListNoteAction.OnDeleteClicked -> delete(action.note)
            is ListNoteAction.OnSaveClicked -> save(action.note)
        }
    }

}