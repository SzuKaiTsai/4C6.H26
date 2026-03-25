package ca.qc.cstj.remotedatasource.core.extensions

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import ca.qc.cstj.remotedatasource.R
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

@SuppressLint("LocalContextResourcesRead")
@Composable
fun painterFromString(resourceName: String): Painter {
    val context = LocalContext.current

    val resId = remember(resourceName) {
        context.resources.getIdentifier(
            resourceName,
            "drawable",
            context.packageName
        )
    }

    return if (resId != 0) {
        painterResource(id = resId)
    } else {
        painterResource(id = R.drawable.ic_launcher_foreground)
    }
}