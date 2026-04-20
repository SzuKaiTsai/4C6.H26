package ca.qc.cstj.funmania.ui.screens.barcode

import androidx.annotation.StringRes

sealed interface BarcodeAction {
    //TODO: OnScanResult
    data object Refresh : BarcodeAction
}

sealed interface BarcodeEvent {
    data class OnError(@field:StringRes val errorRes: Int) : BarcodeEvent
}