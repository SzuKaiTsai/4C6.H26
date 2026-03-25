package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import ca.qc.cstj.remotedatasource.core.AsyncResult
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.model.Planet

data class PlanetsListUiState(
    val temperatureUnit: Constants.TemperatureUnit = Constants.TemperatureUnit.Celsius,
    val planetResult: AsyncResult<List<Planet>> = AsyncResult.Loading,
    val isRefreshing: Boolean = false
)