package ca.qc.cstj.funmania.ui.screens.weather

sealed interface WeatherAction {
    data class OnUpdateSearchText(val search:String): WeatherAction
    data object OnSearch: WeatherAction
}