package ca.qc.cstj.funmania.ui.screens.barcode

import ca.qc.cstj.funmania.core.AsyncResult
import ca.qc.cstj.funmania.models.CheckIn

data class BarcodeUiState(
    val checkInsResult: AsyncResult<List<CheckIn>> = AsyncResult.Loading
)
