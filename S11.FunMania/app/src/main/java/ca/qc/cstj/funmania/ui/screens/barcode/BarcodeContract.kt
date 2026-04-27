package ca.qc.cstj.funmania.ui.screens.barcode

import androidx.annotation.StringRes

sealed interface BarcodeAction {
    data class OnScan(val qrContent: String?) : BarcodeAction
    data object Refresh : BarcodeAction
}

sealed interface BarcodeEvent {
    data class OnError(@field:StringRes val errorRes: Int) : BarcodeEvent
}