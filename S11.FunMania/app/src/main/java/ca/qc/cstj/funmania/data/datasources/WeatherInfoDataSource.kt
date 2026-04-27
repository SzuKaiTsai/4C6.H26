package ca.qc.cstj.funmania.data.datasources

import ca.qc.cstj.funmania.core.Constants
import ca.qc.cstj.funmania.data.datasources.dto.WeatherInfoDTO
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class WeatherInfoDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun retrieveWithCityName(cityName: String): WeatherInfoDTO {
        val url = "${Constants.NetworkEndPoint.CURRENT_WEATHER_END_POINT}?q=${cityName}${Constants.NetworkEndPoint.DEFAULTS_OPTIONS}"

        val (_, _, result) = url.httpGet().responseJson()

        return when(result){
            is Result.Failure -> throw result.error
            is Result.Success -> json.decodeFromString(result.value.content)
        }
    }
}