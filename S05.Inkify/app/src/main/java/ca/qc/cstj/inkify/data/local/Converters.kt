package ca.qc.cstj.inkify.data.local

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class Converters {

    @TypeConverter
    fun toLocalDateTime(dateTime: String) : LocalDateTime{
        return LocalDateTime.parse(dateTime)
    }

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime) : String{
        return dateTime.toString()
    }
}