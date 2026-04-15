package ca.qc.cstj.bottomnavigation.ui.screens.features


import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FeaturesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FeatureUiState())
    val uiState = _uiState.asStateFlow()

    private val timer = object: CountDownTimer(10000, 1000){
        override fun onFinish() {
            _uiState.update {
                it.copy(isFinished = true)
            }
        }

        override fun onTick(p0: Long) {
            _uiState.update {
                it.copy(progression = it.progression + 1)
            }
        }

    }

    init {
        timer.start()
    }
}