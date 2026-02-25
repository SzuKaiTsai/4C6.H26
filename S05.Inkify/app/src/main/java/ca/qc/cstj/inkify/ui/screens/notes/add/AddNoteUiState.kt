package ca.qc.cstj.inkify.ui.screens.notes.add

import ca.qc.cstj.inkify.models.Note


data class AddNoteUiState(
    val newNote: Note = Note()
)