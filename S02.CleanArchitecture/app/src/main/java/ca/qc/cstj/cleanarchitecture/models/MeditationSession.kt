package ca.qc.cstj.cleanarchitecture.models

data class MeditationSession(
    val title: String,
    val durationMinutes: Int,
    val tags: List<String>
)