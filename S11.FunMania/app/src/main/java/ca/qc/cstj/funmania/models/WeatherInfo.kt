package ca.qc.cstj.funmania.models

import ca.qc.cstj.funmania.core.DateHelper
import ca.qc.cstj.funmania.data.datasources.dto.WeatherInfoDTO

data class WeatherInfo(private val weatherInfoDTO: WeatherInfoDTO) {
    val city = weatherInfoDTO.name ?: ""
    val country = weatherInfoDTO.sys?.country ?: ""
    val temperature = weatherInfoDTO.main?.temp ?: 0.0
    val feelsLike = weatherInfoDTO.main?.feelsLike ?: 0.0
    val weather = weatherInfoDTO.weather?.get(0)?.main ?: ""
    val description= weatherInfoDTO.weather?.get(0)?.description ?: ""
    val icon  = weatherInfoDTO.weather?.get(0)?.icon ?: ""
    val humidity = weatherInfoDTO.main?.humidity ?: 0
    val wind = weatherInfoDTO.wind?.speed ?: 0.0
    val longitude = weatherInfoDTO.coord?.lon ?: 0.0
    val latitude = weatherInfoDTO.coord?.lat ?: 0.0
    val systemDefaultDate= DateHelper.toSystemDefaultDateTime(weatherInfoDTO.dt ?: 0, weatherInfoDTO.timezone ?:0)
    val locationDateTime = DateHelper.toCurrentWeatherLocationDateTime(weatherInfoDTO.dt ?: 0, weatherInfoDTO.timezone ?:0)
    val timestamp= weatherInfoDTO.dt ?: 0
    val timezone = weatherInfoDTO.timezone ?: 0
}