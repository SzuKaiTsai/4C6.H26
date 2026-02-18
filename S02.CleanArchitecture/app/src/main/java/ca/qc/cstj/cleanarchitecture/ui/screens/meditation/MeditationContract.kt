package ca.qc.cstj.cleanarchitecture.ui.screens.meditation

import ca.qc.cstj.cleanarchitecture.models.MeditationSession

sealed interface MeditationAction {
    data class OnStartMeditationClicked(val meditationSession : MeditationSession) : MeditationAction
    data class OnSearch(val searchText : String) : MeditationAction
    data class OnTagClicked(val tagFilter:MeditationFilter = MeditationFilter.All) : MeditationAction
    data class OnNameChange(val name: String) : MeditationAction
}