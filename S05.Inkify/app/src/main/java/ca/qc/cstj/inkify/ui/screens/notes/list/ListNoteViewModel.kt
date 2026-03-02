package ca.qc.cstj.inkify.ui.screens.notes.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.inkify.InkifyApplication
import ca.qc.cstj.inkify.data.repositories.NoteRepository
import ca.qc.cstj.inkify.models.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
//       le code suivant est la même chose que le code avant - juste une maniere differente
//       noteRepository.retrieveAll().onEach { notes ->
//           _uiState.update {
//               it.copy(notes=notes)
//           }
//       }.launchIn(viewModelScope)
    }

    private fun delete(note: Note) {
        viewModelScope.launch {
            try {
                noteRepository.deleteOne(note)
            } catch (ex: Exception) {

            }
        }
    }

    fun save(note: Note) {
        viewModelScope.launch {
            try {
                noteRepository.update(note)
            } catch (ex: Exception) {

            }
        }
    }

    fun onAction(action: ListNoteAction) {
        when(action) {
            is ListNoteAction.OnDeleteClicked -> delete(action.note)
            is ListNoteAction.OnSaveClicked -> save(action.note)
        }
    }

}