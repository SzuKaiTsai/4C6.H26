package ca.qc.cstj.funmania.data.repositories

import ca.qc.cstj.funmania.data.datasources.CheckInDataSource
import ca.qc.cstj.funmania.models.CheckIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CheckInRepository(
    private val checkInDataSource: CheckInDataSource = CheckInDataSource()
) {
    fun retrieveAll(): Flow<List<CheckIn>> {
        return flow {
            emit(checkInDataSource.retrieveAll())
        }.catch { ex ->
            throw ex
        }.flowOn(Dispatchers.IO)

    }

    fun create(checkIn: CheckIn): Flow<CheckIn> {
        return flow {
            emit(checkInDataSource.create(checkIn))
        }.catch { ex ->
            throw ex
        }.flowOn(Dispatchers.IO)
    }
}