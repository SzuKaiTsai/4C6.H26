package ca.qc.cstj.funmania.data.repositories

import ca.qc.cstj.funmania.data.datasources.WeatherInfoDataSource
import ca.qc.cstj.funmania.models.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherInfoRepository(
    private val weatherInfoDataSource: WeatherInfoDataSource = WeatherInfoDataSource()
) {

    fun retrieveWithCityName(cityName:String) : Flow<WeatherInfo> {
        return flow {
            val weatherInfoDto = weatherInfoDataSource.retrieveWithCityName(cityName)
            emit(WeatherInfo(weatherInfoDto))
        }.catch { ex ->
            throw ex
        }.flowOn(Dispatchers.IO)

    }
}