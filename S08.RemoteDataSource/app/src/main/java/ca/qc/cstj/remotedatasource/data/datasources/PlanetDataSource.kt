package ca.qc.cstj.remotedatasource.data.datasources

import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.model.Planet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class PlanetDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun retrieveAll() : List<Planet> {

        val (_, _, result) = Constants.BaseURL.PLANETS.httpGet().responseJson()

        return when(result){
            is Result.Failure -> throw result.error.exception
            is Result.Success -> json.decodeFromString(result.value.content)
        }

    }

    fun retrieveOne() {
        //TODO:
    }

}