package ca.qc.cstj.inkify.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ca.qc.cstj.inkify.data.local.entities.NoteEntity
import ca.qc.cstj.inkify.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // CREATE
    @Insert
    suspend fun create(note: NoteEntity)

    @Query("SELECT * FROM notes")
    fun retrieveAll(): Flow<List<NoteEntity>>

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun deleteOne(note: NoteEntity)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()

}