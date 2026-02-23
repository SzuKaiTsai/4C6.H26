package ca.qc.cstj.inkify.core

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.Clock

val String.toColor: Color
    @SuppressLint("UseKtx")
    get() = Color(android.graphics.Color.parseColor("#$this"))

val Color.toHex: String
    get() = this.toArgb().toHexString(HexFormat.Default)

fun LocalDateTime.Companion.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

fun LocalDateTime.toIsoString(): String {
    return this.toString()
}

fun LocalDateTime.format(format: String = Constants.DATETIME_PATTERN): String {
    val formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
    return this.toJavaLocalDateTime().format(formatter)
}

@SuppressLint("DiscouragedApi")
fun Context.getStringWithIdentifier(identifier:String) : String {
    val res = this.resources
    return res.getString(res.getIdentifier(identifier.lowercase(), "string", packageName))
}

@SuppressLint("DiscouragedApi")
fun Context.loadFromResource(context: Context, imageName:String) : Int {
    return resources.getIdentifier(imageName.lowercase(), "drawable", context.packageName)
}

fun Context.stringResourceWithContext(@StringRes resId: Int, vararg args: Any) : String = getString(resId, *args)
fun Context.stringResourceWithContext(@StringRes resId: Int) : String = getString(resId)