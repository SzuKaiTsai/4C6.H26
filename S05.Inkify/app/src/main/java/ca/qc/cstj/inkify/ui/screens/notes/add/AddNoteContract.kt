package ca.qc.cstj.inkify.ui.screens.notes.add

import androidx.compose.ui.graphics.Color

sealed interface AddNoteAction {
    data class OnUpdateColor(val color: Color): AddNoteAction
    data class OnUpdateTitle(val title: String): AddNoteAction
    data class OnUpdateContent(val content: String): AddNoteAction
    data object OnSaveClicked: AddNoteAction

}

sealed interface AddNoteEvent {

}