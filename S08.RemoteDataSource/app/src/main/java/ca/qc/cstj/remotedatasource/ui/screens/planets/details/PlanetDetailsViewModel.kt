package ca.qc.cstj.remotedatasource.ui.screens.planets.details

import androidx.lifecycle.ViewModel
import ca.qc.cstj.remotedatasource.data.repositories.PlanetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlanetDetailsViewModel(private val href: String) : ViewModel() {

    private val _uiState = MutableStateFlow(PlanetDetailsScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val planetRepository = PlanetRepository()

    private fun retrievePlanet() {

    }

}