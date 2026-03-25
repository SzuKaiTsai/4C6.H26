package ca.qc.cstj.remotedatasource.ui.navigation


sealed interface Route {
    data object PlanetList : Route
    data class PlanetDetail(val href:String) : Route
}