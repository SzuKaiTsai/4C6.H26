package ca.qc.cstj.inkify.ui.screens.notes.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListNoteScreen(
    viewModel: ListNoteViewModel = viewModel(),
    toAddNoteScreen: () -> Unit,
    toSettingsScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    //Scaffold

}

@Composable
private fun ListNoteContent(
    modifier: Modifier = Modifier,
    uiState: ListNoteUiState = ListNoteUiState(),
    onAction: (ListNoteAction) -> Unit = {},
) {

    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        items(uiState.notes) { note ->

        }
    }
}