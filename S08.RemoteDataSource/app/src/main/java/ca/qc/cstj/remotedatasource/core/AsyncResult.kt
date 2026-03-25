package ca.qc.cstj.remotedatasource.core

import androidx.annotation.StringRes

sealed interface AsyncResult<out T> {
    data object Loading : AsyncResult<Nothing>
    data class Success<T>(val data: T) : AsyncResult<T>
    data class Error(@field:StringRes val messageResId: Int) : AsyncResult<Nothing>
}