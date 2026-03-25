package ca.qc.cstj.remotedatasource.ui.screens.planets.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PlanetDetailsScreen(
    href: String,
    viewModel: PlanetDetailsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    //TODO:
}