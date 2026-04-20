package ca.qc.cstj.funmania.ui.coordinators

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TopLevelCoordinator(
    viewModel: TopLevelViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val backStack = uiState.backStack

    //TODO:
}