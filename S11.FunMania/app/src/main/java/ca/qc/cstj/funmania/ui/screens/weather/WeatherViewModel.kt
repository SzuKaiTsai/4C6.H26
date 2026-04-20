package ca.qc.cstj.funmania.ui.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.funmania.R
import ca.qc.cstj.funmania.core.AsyncAction
import ca.qc.cstj.funmania.core.Constants
import ca.qc.cstj.funmania.data.repositories.WeatherInfoRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    private val weatherInfoRepository = WeatherInfoRepository()

    private var refreshJob: Job? = null

    private fun updateSearchText(search: String) {
        _uiState.update {
            it.copy(search = search)
        }
    }

    private fun search() {
        refreshJob?.cancel()
        refreshJob = viewModelScope.launch {
            while(isActive) {
                _uiState.update {
                it.copy(currentWeatherResult = AsyncAction.Loading)
                }
                weatherInfoRepository.retrieveWithCityName(uiState.value.search).catch {
                    _uiState.update {
                        it.copy(currentWeatherResult = AsyncAction.Error(R.string.server_error))
                    }
                }.collect { weatherInfo ->
                    _uiState.update {
                        it.copy(currentWeatherResult = AsyncAction.Success(weatherInfo))
                    }
                }
                delay(Constants.RefreshDelay.METEO_REFRESH)
            }

        }
    }

    fun onAction(action: WeatherAction) {
        when(action) {
            is WeatherAction.OnUpdateSearchText -> updateSearchText(action.search)
            WeatherAction.OnSearch -> search()
        }
    }

}