package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import ca.qc.cstj.remotedatasource.core.Constants

sealed interface PlanetsListAction {
    data class ChangeTemperatureUnit(val unit: Constants.TemperatureUnit) : PlanetsListAction
    data object RefreshPlanet: PlanetsListAction
}