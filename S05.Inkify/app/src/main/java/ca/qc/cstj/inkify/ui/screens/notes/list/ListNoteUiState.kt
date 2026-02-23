package ca.qc.cstj.inkify.ui.screens.notes.list

import ca.qc.cstj.inkify.models.Note

data class ListNoteUiState(
    val notes: List<Note> = listOf()
)