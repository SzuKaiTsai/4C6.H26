package ca.qc.cstj.inkify.ui.screens.notes.add

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.inkify.R
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.core.ObserveAsEvents
import ca.qc.cstj.inkify.core.stringResourceWithContext
import ca.qc.cstj.inkify.core.toColor
import ca.qc.cstj.inkify.ui.components.InkifyTopBar


@Composable
fun AddNoteScreen(
    viewModel: AddNoteViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    toSettingsScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    //Events
    ObserveAsEvents(viewModel.events) { event->
        when(event){
            AddNoteEvent.NoteError -> {
                Toast.makeText(context, R.string.error_while_saving, Toast.LENGTH_LONG).show()
            }
            is AddNoteEvent.NoteSaved -> {
                Toast.makeText(
                    context,
                    context.stringResourceWithContext(R.string.note_saved, event.note.title),
                    Toast.LENGTH_LONG)
                    .show()
                onNavigateBack()
            }
        }
    }

    //Scaffold
    Scaffold(
        topBar = {
            InkifyTopBar(
                onNavigateBack = { onNavigateBack()},
                toSettingsScreen = { toSettingsScreen() }
            )
        },
        floatingActionButton = {

            if(uiState.newNote.isValid()){
                FloatingActionButton(
                    onClick = { viewModel.onAction(AddNoteAction.OnSaveClicked)}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Save, contentDescription = stringResource(R.string.save)
                    )
                }
            }


        }
    ) { innerPaddings ->
        AddNoteContent(
            modifier = Modifier.padding(innerPaddings),
            uiState = uiState,
            onAction = {viewModel.onAction(it)}
        )
    }


}


@Composable
private fun AddNoteContent(
    modifier: Modifier = Modifier,
    uiState: AddNoteUiState = AddNoteUiState(),
    onAction: (AddNoteAction) -> Unit = {}
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.new_note), style = MaterialTheme.typography.displaySmall
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.newNote.title,
            onValueChange = {
                onAction(AddNoteAction.OnUpdateTitle(it))
            },
            label = {
                Text(text = stringResource(R.string.title))
            },
            isError = !uiState.newNote.isValid(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(Constants.NOTES_COLORS) {
                Box(
                    modifier = Modifier
                        .padding(
                            end = 8.dp, top = 8.dp
                        )
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(it.toColor)
                        .clickable {
                            onAction(AddNoteAction.OnUpdateColor(it.toColor))
                        }) {
                    if(uiState.newNote.color == it.toColor){
                        Icon(
                            imageVector = Icons.Sharp.CheckCircle,
                            contentDescription = Icons.Sharp.CheckCircle.toString(),
                            tint = Color.Black,
                            modifier = Modifier.align(
                                alignment = Alignment.Center
                            )
                        )
                    }
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            value = uiState.newNote.content,
            onValueChange = {
                onAction(AddNoteAction.OnUpdateContent(it))
            },
            isError = !uiState.newNote.isValid(),
            singleLine = false,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {})
        )
    }
}