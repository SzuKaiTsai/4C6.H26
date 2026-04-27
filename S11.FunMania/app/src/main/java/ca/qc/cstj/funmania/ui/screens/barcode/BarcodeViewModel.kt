package ca.qc.cstj.funmania.ui.screens.barcode

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.funmania.R
import ca.qc.cstj.funmania.core.AsyncAction
import ca.qc.cstj.funmania.core.AsyncResult
import ca.qc.cstj.funmania.core.Constants
import ca.qc.cstj.funmania.data.repositories.CheckInRepository
import ca.qc.cstj.funmania.models.CheckIn
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BarcodeViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(BarcodeUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<BarcodeEvent>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private val checkInRepository = CheckInRepository()

    private fun refreshCheckIns() {
        viewModelScope.launch {
            checkInRepository.retrieveAll().catch {
                _uiState.update {
                    it.copy(checkInsResult = AsyncResult.Error(R.string.server_error))
                }
            }.collect { checkIns ->
                _uiState.update {
                    it.copy(checkInsResult = AsyncResult.Success(checkIns))
                }
            }
        }
    }


    fun onAction(action: BarcodeAction) {
        when (action) {
            BarcodeAction.Refresh -> refreshCheckIns()
            is BarcodeAction.OnScan -> onScanResult(action.qrContent)
        }
    }

    private fun onScanResult(rawQR: String?) {
        viewModelScope.launch {
            if(rawQR == null) {
                _events.send(BarcodeEvent.OnError(R.string.barcode_empty))
                return@launch
            }

            val checkIn = CheckIn(scanId = rawQR, door = Constants.DOOR)

            checkInRepository.create(checkIn).catch {
                _events.send(BarcodeEvent.OnError(R.string.error_while_saving))
            }.collect {
                Log.d("CHECK-IN", it.scanId)
            }
        }
    }

}