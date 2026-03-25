package ca.qc.cstj.remotedatasource.model

import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val name: String,
    val temperature: Float,
    val icon: String,
    val href: String,
    val portals: List<Portal>
)

@Serializable
data class Portal(
    val position:String,
    val affinity: String
)

