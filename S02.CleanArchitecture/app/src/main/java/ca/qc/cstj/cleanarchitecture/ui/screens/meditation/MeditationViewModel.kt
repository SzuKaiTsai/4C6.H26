package ca.qc.cstj.cleanarchitecture.ui.screens.meditation

import android.nfc.Tag
import androidx.lifecycle.ViewModel
import ca.qc.cstj.cleanarchitecture.data.MockData
import ca.qc.cstj.cleanarchitecture.models.MeditationSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MeditationViewModel(name: String): ViewModel() {
    private val _uiState = MutableStateFlow(MeditationUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(name = name)
        }
    }

    private fun startMeditation(meditationSession: MeditationSession) {
        _uiState.update {
            currentState -> currentState.copy(currentMeditationSession = meditationSession)
        }
    }

    private fun search(searchText: String) {
        _uiState.update {
            currentState -> currentState.copy(searchText = searchText,
                featuresMeditationSession = MockData.meditationSessions.filter {
                    m -> m.title.contains(searchText, true) ||
                        m.with.contains(searchText, true)
                })
        }
    }

    private fun changeTag(filterTag: MeditationFilter) {

        val meditationSessions = when(filterTag){
            MeditationFilter.All -> MockData.meditationSessions
            is MeditationFilter.ByTag -> MockData.meditationSessions.filter { m->m.tags.contains(filterTag.tag) }
        }
        _uiState.update {
            currentState -> currentState.copy(tagFilter = filterTag,
                featuresMeditationSession = meditationSessions
                )
        }
    }

    private fun updateName(name: String) {
       _uiState.update { it.copy(name = name) }
    }

    fun onAction(action: MeditationAction){
        when(action){
            is MeditationAction.OnStartMeditationClicked -> startMeditation(action.meditationSession)
            is MeditationAction.OnSearch -> search(action.searchText)
            is MeditationAction.OnTagClicked -> changeTag(action.tagFilter)
            is MeditationAction.OnNameChange -> updateName(action.name)
        }
    }




}