package ca.qc.cstj.funmania.ui.screens.barcode

import androidx.lifecycle.ViewModel
import ca.qc.cstj.funmania.data.repositories.CheckInRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class BarcodeViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(BarcodeUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<BarcodeEvent>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private val checkInRepository = CheckInRepository()

    private fun refreshCheckIns() {
       //TODO:
    }


    fun onAction(action: BarcodeAction) {
        when (action) {
            BarcodeAction.Refresh -> refreshCheckIns()
        }
    }

    private fun onScanResult(rawQR: String?) {
        //TODO:
    }

}