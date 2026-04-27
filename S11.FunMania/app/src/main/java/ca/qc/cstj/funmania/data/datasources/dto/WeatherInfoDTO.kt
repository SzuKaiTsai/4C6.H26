package ca.qc.cstj.funmania.data.datasources.dto
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class WeatherInfoDTO(
    @SerialName("base")
    val base: String? = "",
    @SerialName("clouds")
    val clouds: Clouds? = Clouds(),
    @SerialName("cod")
    val cod: Int? = 0,
    @SerialName("coord")
    val coord: Coord? = Coord(),
    @SerialName("dt")
    val dt: Int? = 0,
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("main")
    val main: Main? = Main(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("sys")
    val sys: Sys? = Sys(),
    @SerialName("timezone")
    val timezone: Int? = 0,
    @SerialName("visibility")
    val visibility: Int? = 0,
    @SerialName("weather")
    val weather: List<Weather?>? = listOf(),
    @SerialName("wind")
    val wind: Wind? = Wind()
)

@Serializable
data class Clouds(
    @SerialName("all")
    val all: Int? = 0
)

@Serializable
data class Coord(
    @SerialName("lat")
    val lat: Double? = 0.0,
    @SerialName("lon")
    val lon: Double? = 0.0
)

@Serializable
data class Main(
    @SerialName("feels_like")
    val feelsLike: Double? = 0.0,
    @SerialName("grnd_level")
    val grndLevel: Int? = 0,
    @SerialName("humidity")
    val humidity: Int? = 0,
    @SerialName("pressure")
    val pressure: Int? = 0,
    @SerialName("sea_level")
    val seaLevel: Int? = 0,
    @SerialName("temp")
    val temp: Double? = 0.0,
    @SerialName("temp_max")
    val tempMax: Double? = 0.0,
    @SerialName("temp_min")
    val tempMin: Double? = 0.0
)

@Serializable
data class Sys(
    @SerialName("country")
    val country: String? = "",
    @SerialName("sunrise")
    val sunrise: Int? = 0,
    @SerialName("sunset")
    val sunset: Int? = 0
)

@Serializable
data class Weather(
    @SerialName("description")
    val description: String? = "",
    @SerialName("icon")
    val icon: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("main")
    val main: String? = ""
)

@Serializable
data class Wind(
    @SerialName("deg")
    val deg: Int? = 0,
    @SerialName("gust")
    val gust: Double? = 0.0,
    @SerialName("speed")
    val speed: Double? = 0.0
)


