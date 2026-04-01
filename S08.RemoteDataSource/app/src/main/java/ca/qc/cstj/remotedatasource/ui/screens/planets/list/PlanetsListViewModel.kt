package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.remotedatasource.R
import ca.qc.cstj.remotedatasource.core.AsyncResult
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.data.repositories.PlanetRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class PlanetsListViewModel : ViewModel() {

    private val planetRepository = PlanetRepository()

    private val _uiState = MutableStateFlow(PlanetsListUiState())
    val uiState = _uiState.asStateFlow()

    private var refreshPlateJob: Job? = null

    init {
        refreshPlanet()
    }

    fun onAction(action: PlanetsListAction) {
        when (action) {
            is PlanetsListAction.ChangeTemperatureUnit -> changeTemperatureUnit(action.unit)
            PlanetsListAction.RefreshPlanet -> refreshPlanet()
        }
    }

    private fun changeTemperatureUnit(unit: Constants.TemperatureUnit) {
        _uiState.update {
            it.copy(temperatureUnit = unit)
        }
        refreshPlanet()
    }

    private fun refreshPlanet() {

        refreshPlateJob?.cancel()

        refreshPlateJob = viewModelScope.launch {
            while (isActive) {
                val isAlreadyLoaded = _uiState.value.planetResult is AsyncResult.Success


                _uiState.update {
                    if (isAlreadyLoaded) { //2e refresh ou plus
                        it.copy(isRefreshing = true)
                    } else {
                        it.copy(planetResult = AsyncResult.Loading)
                    }

                }

                planetRepository.retrieveAll(_uiState.value.temperatureUnit)
                    .catch { //Erreur lors de la requete http
                        _uiState.update {
                            it.copy(
                                planetResult = AsyncResult.Error(R.string.error),
                                isRefreshing = false
                            )
                        }
                    }
                    .collect { planets ->
                        _uiState.update {
                            it.copy(
                                planetResult = AsyncResult.Success(planets),
                                isRefreshing = false
                            )
                        }
                    }
                delay(Constants.RefreshDelay.PLANET_REFRESH_DELAY)
            }
        }

    }
}