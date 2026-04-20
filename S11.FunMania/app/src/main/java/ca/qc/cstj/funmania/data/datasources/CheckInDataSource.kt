package ca.qc.cstj.funmania.data.datasources

import ca.qc.cstj.funmania.core.Constants
import ca.qc.cstj.funmania.models.CheckIn
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class CheckInDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun create(checkIn: CheckIn): CheckIn {
        //TODO
        return CheckIn("yannick", 2)
    }

    fun retrieveAll() :List<CheckIn> {
        val (_, _, result) = Constants.BaseURL.CHECKIN_URL.httpGet().responseJson()

        return when (result) {
            is Result.Success -> json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.getException().exception
        }

    }
}