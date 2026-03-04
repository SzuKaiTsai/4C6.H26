package ca.qc.cstj.inkify.ui.screens.notes.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.inkify.ui.components.InkifyTopBar
import ca.qc.cstj.inkify.ui.components.NoteCard

@Composable
fun ListNoteScreen(
    viewModel: ListNoteViewModel = viewModel(),
    toAddNoteScreen: () -> Unit,
    toSettingsScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    //Scaffold
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            InkifyTopBar(
                toSettingsScreen = { toSettingsScreen() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    toAddNoteScreen()
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = Icons.Filled.Add.name)
            }
        }
    ) { innerPaddings->
        ListNoteContent(
            modifier = Modifier.padding(innerPaddings),
            uiState = uiState,
            onAction = { viewModel.onAction(it) }
            )
    }

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
            NoteCard(note = note,
                onSaveClick = { onAction(ListNoteAction.OnSaveClicked(it)) },
                onDeleteClick = { onAction(ListNoteAction.OnDeleteClicked(it)) })
        }
    }
}