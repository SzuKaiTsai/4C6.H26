package ca.qc.cstj.inkify.models

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDateTime

data class Note(
    private val idNote: Int,
    val title:String,
    val color: Color,
    val content: String,
    val creationDate: LocalDateTime
) {

}
