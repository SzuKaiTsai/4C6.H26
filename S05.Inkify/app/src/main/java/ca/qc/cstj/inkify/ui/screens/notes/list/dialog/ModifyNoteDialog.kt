package ca.qc.cstj.inkify.ui.screens.notes.list.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.core.toColor
import ca.qc.cstj.inkify.models.Note

@Composable
fun ModifyNoteDialog(
    note: Note, saveAction: (Note) -> Unit, onDismissRequest: () -> Unit
) {

    var currentNote by remember {
        mutableStateOf(note)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                OutlinedTextField(
//                    value = currentNote.title,
//                    onValueChange = {
//                        currentNote = currentNote.copy(title = it)
//                    },
//                    isError = !currentNote.isValid(),
//                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
//
//                )
//                OutlinedTextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(250.dp),
//                    value = currentNote.content,
//                    onValueChange = {
//                        currentNote = currentNote.copy(content = it)
//                    },
//                    isError = !currentNote.isValid(),
//                    singleLine = false,
//                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default)
//                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(Constants.NOTES_COLORS) {
                        Box(modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(it.toColor)
                            .clickable {
                                //currentNote = currentNote.copy(color = it.toColor)
                            }) {
//                            if (currentNote.color == it.toColor) {
//                                Icon(
//                                    imageVector = Icons.Sharp.CheckCircle,
//                                    contentDescription = Icons.Sharp.CheckCircle.toString(),
//                                    tint = Color.Black,
//                                    modifier = Modifier.align(
//                                        Alignment.Center
//                                    )
//                                )
//                            }
                        }
                    }
                }

                //TODO: Afficher seulement si la note est dans un état valide
                    FloatingActionButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = {
                            saveAction(currentNote)
                            onDismissRequest()
                        }
                    ) {
                        Icon(Icons.Filled.Save, contentDescription = Icons.Filled.Add.name)
                    }
                }

        }
    }
}