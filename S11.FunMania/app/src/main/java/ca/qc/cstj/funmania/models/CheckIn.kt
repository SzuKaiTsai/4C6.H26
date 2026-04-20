package ca.qc.cstj.funmania.models

import kotlinx.serialization.Serializable

@Serializable
data class CheckIn(
    val scanId:String,
    val door: Int,
    val scannedDate: String = ""
)