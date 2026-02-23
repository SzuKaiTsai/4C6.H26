package ca.qc.cstj.inkify.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.core.now
import kotlinx.datetime.LocalDateTime

 @Entity(tableName = "notes")
data class NoteEntity(
     @PrimaryKey (true) val idNote: Int,
    val title:String,
    val color: String = Constants.NOTES_COLORS.random(),
    val content: String,
    val creationDate: LocalDateTime = LocalDateTime.now()
) {

}