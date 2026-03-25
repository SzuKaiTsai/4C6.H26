package ca.qc.cstj.remotedatasource.core

object Constants {
    const val IMAGE_URL = "https://assets.andromia.science/planets/%s.png"
    const val ZERO_KELVIN = 273.15f
    const val FAHRENHEIT_FACTOR = 1.8f
    const val FAHRENHEIT_ZERO = 32f

    object BaseURL {
        private const val BASE_API = "https://api.andromia.science"
        const val PLANETS = "${BASE_API}/planets"
    }

    object RefreshDelay {
        const val PLANET_REFRESH_DELAY = 30_000L
    }

    enum class TemperatureUnit {
        Kelvin,
        Celsius,
        Fahrenheit
    }
}