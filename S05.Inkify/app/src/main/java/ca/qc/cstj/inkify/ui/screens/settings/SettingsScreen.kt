package ca.qc.cstj.inkify.ui.screens.settings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.inkify.R
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.core.ObserveAsEvents
import ca.qc.cstj.inkify.core.stringResourceWithContext
import ca.qc.cstj.inkify.core.toColor

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            SettingsEvent.Error -> {
                Toast.makeText(context, context.stringResourceWithContext(R.string.error_while_saving_settings), Toast.LENGTH_LONG).show()
            }
            SettingsEvent.Saved -> {
                Toast.makeText(context, context.stringResourceWithContext(R.string.settings_saved), Toast.LENGTH_LONG).show()
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { }
    ) { innerPadding ->
        SettingsContent(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState,
            onAction = { viewModel.onAction(it) }
        )
    }

}

@Composable
private fun SettingsContent(
    modifier: Modifier = Modifier,
    uiState: SettingsUiState = SettingsUiState(),
    onAction: (SettingsAction) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxHeight()
    ) {
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = uiState.settings.name,
//            onValueChange = { newName ->
//                onAction(SettingsAction.OnSaveName(newName))
//            },
//            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//            label = { Text(text = stringResource(R.string.name)) },
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.AccountCircle,
//                    contentDescription = stringResource(R.string.name),
//                    tint = MaterialTheme.colorScheme.outline
//                )
//            }
//        )

        LazyVerticalGrid(
            modifier = Modifier,
            contentPadding = PaddingValues(8.dp),
            columns = GridCells.Fixed(4)
        ) {
            items(Constants.NOTES_COLORS) {
                Box(modifier = Modifier
                    .padding(
                        end = 8.dp, top = 8.dp
                    )
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(it.toColor)
                    .clickable {
                        onAction(SettingsAction.OnColorChange(it))
                    }) {
//                    if (uiState.settings.noteDefaultColor == it) {
//                        Icon(
//                            imageVector = Icons.Sharp.CheckCircle,
//                            contentDescription = Icons.Sharp.CheckCircle.toString(),
//                            tint = Color.Black,
//                            modifier = Modifier.align(
//                                Alignment.Center
//                            )
//                        )
//                    }
                }
            }
        }


        ElevatedButton(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            onClick = {
                onAction(SettingsAction.OnResetSettings)
            }
        ) {
            Text(text = stringResource(R.string.reset), color = MaterialTheme.colorScheme.onPrimaryContainer )
        }
    }
}