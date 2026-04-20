package ca.qc.cstj.funmania.core

import kotlinx.datetime.LocalDateTime
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object DateHelper {

    fun toSystemDefaultDateTime(timestamp: Int, timezone: Int): LocalDateTime {
        val formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

        val instant = Instant.ofEpochSecond(timestamp.toLong()).atZone(ZoneOffset.UTC)
        val dateTimeString = formatter.format(instant)

        return LocalDateTime.parse(dateTimeString)

    }

    fun toCurrentWeatherLocationDateTime(timestamp: Int, timezone: Int) : LocalDateTime {
        val formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

        val instant = Instant.ofEpochSecond(timestamp.toLong()).atZone(ZoneOffset.UTC).plusSeconds(timezone.toLong())
        val dateTimeString = formatter.format(instant)

        return LocalDateTime.parse(dateTimeString)

    }

}