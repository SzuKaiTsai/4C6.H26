package ca.qc.cstj.remotedatasource.ui.screens.planets.details

import ca.qc.cstj.remotedatasource.core.AsyncResult
import ca.qc.cstj.remotedatasource.model.Planet

data class PlanetDetailsScreenUiState(
    val planetResult: AsyncResult<Planet> = AsyncResult.Loading
)

