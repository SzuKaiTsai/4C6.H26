package ca.qc.cstj.funmania.core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> ObserveAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(onEvent)
        }
    }
}

@Composable
fun OnResume(lifecycleEvent: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                lifecycleEvent()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

//    val lifecycleOwner = LocalLifecycleOwner.current
//    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsStateWithLifecycle()
//
//    LaunchedEffect(lifecycleState) {
//        when (lifecycleState) {
//            Lifecycle.State.DESTROYED -> {}
//            Lifecycle.State.INITIALIZED -> {}
//            Lifecycle.State.CREATED -> {}
//            Lifecycle.State.STARTED -> {}
//            Lifecycle.State.RESUMED -> {
//                viewModel.onAction(BarcodeAction.Refresh)
//            }
//        }
//    }