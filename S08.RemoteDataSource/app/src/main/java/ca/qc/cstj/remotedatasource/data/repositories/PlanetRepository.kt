package ca.qc.cstj.remotedatasource.data.repositories

import android.util.Log
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.data.datasources.PlanetDataSource
import ca.qc.cstj.remotedatasource.model.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class PlanetRepository {

    private val planetDataSource = PlanetDataSource()

    fun retrieveAll(unit: Constants.TemperatureUnit) : Flow<List<Planet>>{
        return flow {

            val planets = planetDataSource.retrieveAll().map {
                when(unit) {
                    Constants.TemperatureUnit.Kelvin -> it.copy(temperature = it.temperature + Constants.ZERO_KELVIN)
                    Constants.TemperatureUnit.Celsius -> it
                    Constants.TemperatureUnit.Fahrenheit -> it.copy(temperature = it.temperature * Constants.FAHRENHEIT_FACTOR + Constants.FAHRENHEIT_ZERO)
                }
            }.sortedBy { it.temperature }
            emit(planets)

        }.catch {
            Log.e("PlanetRepository", it.message.toString())
            throw it
        }.flowOn(Dispatchers.IO)

    }

    fun retrieveOne(href: String) : Flow<Planet> {
        return flow {}
    }


}