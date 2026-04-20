package ca.qc.cstj.funmania.core.extensions

import ca.qc.cstj.funmania.core.Constants
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale


fun LocalDateTime.format(format: String = Constants.DATETIME_PATTERN): String {
    val formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
    return this.toJavaLocalDateTime().format(formatter)
}

fun LocalDateTime.toLocalDateTimeFormat() : String {

    val currentZoneOffset = ZoneId.systemDefault().rules.getOffset(this.toJavaLocalDateTime())

    val dateTimeWithOffset = OffsetDateTime.of(this.toJavaLocalDateTime(), ZoneOffset.UTC)
        .withOffsetSameInstant(currentZoneOffset)

    return DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN).format(dateTimeWithOffset)

}