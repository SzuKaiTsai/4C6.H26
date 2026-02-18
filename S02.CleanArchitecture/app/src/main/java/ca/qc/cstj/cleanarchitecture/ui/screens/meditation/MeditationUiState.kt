package ca.qc.cstj.cleanarchitecture.ui.screens.meditation

import ca.qc.cstj.cleanarchitecture.data.MockData
import ca.qc.cstj.cleanarchitecture.models.MeditationSession

data class MeditationUiState(
    val currentMeditationSession: MeditationSession = MockData.meditationSessions.first(),
    val featuresMeditationSession: List<MeditationSession> = MockData.meditationSessions,
    val searchText:String= "",
    val tags:List<String> = MockData.meditationTags,
    val tagFilter: MeditationFilter = MeditationFilter.All,
    val name: String= ""
    )

sealed interface  MeditationFilter {
    data class ByTag(val tag: String): MeditationFilter
    object All : MeditationFilter
}