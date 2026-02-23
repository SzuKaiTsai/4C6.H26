package ca.qc.cstj.inkify.ui.screens.notes.list

import ca.qc.cstj.inkify.models.Note

sealed interface ListNoteAction {
    data class OnDeleteClicked(val note: Note) : ListNoteAction
    data class OnSaveClicked(val note: Note): ListNoteAction
}