package ca.qc.cstj.inkify.ui.screens.notes.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ca.qc.cstj.inkify.models.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListNoteViewModel (application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(ListNoteUiState())
    val uiState = _uiState.asStateFlow()

    //TODO: Repository

    init {
       //TODO:
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