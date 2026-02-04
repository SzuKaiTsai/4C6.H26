package ca.qc.cstj.cleanarchitecture.ui.screens.meditation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MeditationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MeditationUiState())
    val uiState = _uiState.asStateFlow()
}