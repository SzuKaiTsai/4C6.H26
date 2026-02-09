package ca.qc.cstj.cleanarchitecture.models


data class MeditationSession(
    val title: String,
    val subtitle: String,
    val with: String,
    val category: MeditationCategory,
    val durationInMinutes: Int,
    val tags: List<String>,
    val isPremium: Boolean
)

enum class MeditationCategory{
    SLEEP, RELAX, FOCUS
}