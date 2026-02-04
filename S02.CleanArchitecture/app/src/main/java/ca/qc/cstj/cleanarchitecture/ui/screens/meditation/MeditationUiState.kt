package ca.qc.cstj.cleanarchitecture.ui.screens.meditation

import ca.qc.cstj.cleanarchitecture.data.MockData
import ca.qc.cstj.cleanarchitecture.models.MeditationSession

data class MeditationUiState(
    val currentMeditationSession: MeditationSession = MockData.meditationSessions.first(),
    val featuresMeditationSession: List<MeditationSession> = MockData.meditationSessions
    )
