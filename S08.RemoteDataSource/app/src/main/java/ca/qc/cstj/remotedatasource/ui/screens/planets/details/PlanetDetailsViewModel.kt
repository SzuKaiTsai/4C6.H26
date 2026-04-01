package ca.qc.cstj.remotedatasource.ui.screens.planets.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.remotedatasource.R
import ca.qc.cstj.remotedatasource.core.AsyncResult
import ca.qc.cstj.remotedatasource.data.repositories.PlanetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetDetailsViewModel(private val href: String) : ViewModel() {

    private val _uiState = MutableStateFlow(PlanetDetailsScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val planetRepository = PlanetRepository()

    init {
        retrievePlanet()
    }

    private fun retrievePlanet() {
        viewModelScope.launch {
            planetRepository.retrieveOne(href).catch {
                _uiState.update {
                    it.copy(planetResult = AsyncResult.Error(R.string.error))
                }
            }.collect { planet ->
                _uiState.update {
                    it.copy(planetResult = AsyncResult.Success(planet))
                }
            }
        }
    }

}