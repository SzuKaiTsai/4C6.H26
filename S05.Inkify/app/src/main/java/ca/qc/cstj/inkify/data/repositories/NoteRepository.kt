package ca.qc.cstj.inkify.data.repositories

import ca.qc.cstj.inkify.data.local.dao.NoteDao
import ca.qc.cstj.inkify.models.Note
import ca.qc.cstj.inkify.models.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepository(private val noteDao: NoteDao){
    suspend fun create(note: Note){
        noteDao.create(note.toEntity())
    }

    fun retrieveAll(): Flow<List<Note>>{
        return noteDao.retrieveAll().map { notes ->
            notes.map{ it.toModel()}
        }
    }

    suspend fun deleteOne(note: Note) {
        noteDao.deleteOne(note.toEntity())
    }

    suspend fun update(note: Note) {
        noteDao.update(note.toEntity())
    }
}

