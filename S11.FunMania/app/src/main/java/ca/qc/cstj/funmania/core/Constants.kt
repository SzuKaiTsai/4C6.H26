package ca.qc.cstj.funmania.core

object Constants {

    const val DOOR = 7
    const val DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
    const val NIGHT_HOUR = 19

    object BaseURL {
        private const val BASE_API = "https://api.andromia.science"
        const val CHECKIN_URL = "${BASE_API}/check-ins"
    }

    object NetworkEndPoint {
        private const val BASE_URL = "https://api.openweathermap.org"
        const val API_KEY = "41d2f97877b7c86e4f7a1e59eadddaef"
        const val UNITS = "metric"
        const val FORECAST_END_POINT = "$BASE_URL/data/2.5/forecast"
        const val CURRENT_WEATHER_END_POINT = "$BASE_URL/data/2.5/weather"
        const val DEFAULTS_OPTIONS = "&units=${UNITS}&appid=${API_KEY}"
        const val WEATHER_ICON_URL = "https://openweathermap.org/img/w/%s.png"
        const val FLAGS_URL = "https://flagcdn.com/%s.svg"
        const val FLAGS_API_URL = "https://flagsapi.com/%s/flat/64.png"
    }

    object WeatherConditions {
        const val CLEAR_SKY = "clear sky"
        const val FEW_CLOUDS = "few clouds"
        const val SCATTERED_CLOUDS = "scattered clouds"
        const val BROKEN_CLOUDS = "broken clouds"
        const val SHOWER_RAIN = "shower rain"
        const val RAIN = "rain"
        const val THUNDERSTORM = "thunderstorm"
        const val SNOW = "snow"
        const val MIST = "mist"
    }

    object MainWeatherConditions {
        const val CLOUDS = "Clouds"
        const val SNOW = "Snow"
        const val RAIN = "Rain"
        const val THUNDERSTORM = "Thunderstorm"
        const val CLEAR = "Clear"
    }

    object RefreshDelay {
        const val METEO_REFRESH = 1000L * 60
    }

}