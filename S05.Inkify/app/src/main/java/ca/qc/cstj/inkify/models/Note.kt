package ca.qc.cstj.inkify.models

import androidx.compose.ui.graphics.Color
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.core.now
import ca.qc.cstj.inkify.core.toColor
import ca.qc.cstj.inkify.core.toHex
import ca.qc.cstj.inkify.data.local.entities.NoteEntity
import kotlinx.datetime.LocalDateTime

data class Note(
    private val idNote: Int = 0,
    val title:String = "",
    val color: Color = Constants.NOTES_COLORS.random().toColor,
    val content: String = "",
    val creationDate: LocalDateTime = LocalDateTime.now()
) {
    fun isValid(): Boolean{
        return title.isNotBlank() || content.isNotBlank()
    }

    fun toEntity(): NoteEntity{
        return NoteEntity(
            idNote = idNote,
            title = title,
            content = content,
            color = color.toHex,
            creationDate = creationDate
        )
    }
}

fun NoteEntity.toModel(): Note{
    return Note(
        idNote = idNote,
        title = title,
        content = content,
        color = color.toColor,
        creationDate = creationDate
    )
}
