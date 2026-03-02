package ca.qc.cstj.inkify.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.qc.cstj.inkify.R
import ca.qc.cstj.inkify.core.format
import ca.qc.cstj.inkify.models.Note
import ca.qc.cstj.inkify.ui.screens.notes.list.dialog.ModifyNoteDialog

@Composable
fun NoteCard(
    note: Note,
    onSaveClick: (Note) -> Unit,
    onDeleteClick: (Note) -> Unit
) {

    //TODO: Gestion des dialogs
    var editDialog by remember {
        mutableStateOf(false)
    }
    var deleteDialog by remember {
        mutableStateOf(false)
    }


    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .clickable {
                editDialog = true
            },
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = note.color)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = note.title, style = MaterialTheme.typography.titleLarge, color = Color.Black
            )
            Text(
                text = note.content, color = Color.Black
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = note.creationDate.format(), color = Color.Black, style = MaterialTheme.typography.bodyMedium
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = Icons.Default.Delete.toString(),
                    tint = Color.Black,
                    modifier = Modifier.clickable {
                        deleteDialog = deleteDialog.not()

                    })
            }
        }
    }

    if (editDialog){
        ModifyNoteDialog(note = note,
            { onSaveClick(it) },
            {editDialog = false})
    }

    if(deleteDialog){
        AlertDialog(
            onDismissRequest = { deleteDialog = false },
            title = { Text(stringResource(R.string.delete_confirm_msg), style = MaterialTheme.typography.titleMedium) },
            text = {
                Text(
                    stringResource(R.string.this_action_cannot_be_undone),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    deleteDialog = false
                    onDeleteClick(note)
                }) {
                    Text(stringResource(R.string.delete_it).uppercase())
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    deleteDialog = false
                }) {
                    Text(stringResource(R.string.cancel).uppercase())
                }
            },
        )
    }

}

