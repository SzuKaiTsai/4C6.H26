package ca.qc.cstj.inkify.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import ca.qc.cstj.inkify.data.local.entities.NoteEntity

@Dao
interface NoteDao {

    // CREATE
    @Insert
    suspend fun create(note: NoteEntity)

}