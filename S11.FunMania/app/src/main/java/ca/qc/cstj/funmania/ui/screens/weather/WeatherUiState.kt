package ca.qc.cstj.funmania.ui.screens.weather

import ca.qc.cstj.funmania.core.AsyncAction
import ca.qc.cstj.funmania.models.WeatherInfo

data class WeatherUiState(
    val search: String = "",
    val currentWeatherResult: AsyncAction<WeatherInfo> = AsyncAction.Idle
)
