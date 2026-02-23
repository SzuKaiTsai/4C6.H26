package ca.qc.cstj.inkify.ui.screens.notes.add

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.inkify.R
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.core.toColor


@Composable
fun AddNoteScreen(
    viewModel: AddNoteViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    toSettingsScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    //Events


    //Scaffold
    Scaffold() { innerPaddings ->
        AddNoteContent(
            modifier = Modifier.padding(innerPaddings),
            uiState = uiState
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
            value = "TITLE",
            onValueChange = {
                //TODO:
            },
            label = {
                Text(text = stringResource(R.string.title))
            },
            isError = false, //TODO
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
                            //TODO:
                        }) {
                    //TODO: Icon si c'est la couleur selectionnée
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            value = "CONTENT",
            onValueChange = {
                //TODO:
            },
            isError = false, //TODO
            singleLine = false,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {})
        )
    }
}