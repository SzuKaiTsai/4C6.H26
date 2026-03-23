package ca.qc.cstj.inkify.models

import ca.qc.cstj.inkify.core.Constants
import kotlinx.serialization.Serializable

@Serializable
data class InkifySettings(
    val name: String = "",
    val noteDefaultColor: String = Constants.NOTES_COLORS.random()
)