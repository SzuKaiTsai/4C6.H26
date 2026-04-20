package ca.qc.cstj.funmania.ui.screens.barcode

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.funmania.R
import ca.qc.cstj.funmania.core.AsyncResult
import ca.qc.cstj.funmania.core.extensions.ObserveAsEvents
import ca.qc.cstj.funmania.core.ui.components.ErrorMessage
import ca.qc.cstj.funmania.core.ui.components.LoadingAnimation
import ca.qc.cstj.funmania.models.CheckIn

@Composable
fun BarcodeScreen(
    viewModel: BarcodeViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    //TODO: On Resume

    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is BarcodeEvent.OnError -> Toast.makeText(context, event.errorRes, Toast.LENGTH_LONG).show()
        }
    }

    //TODO: ScanLauncher

    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(val checkInsResult = uiState.checkInsResult){
            is AsyncResult.Error ->  ErrorMessage(checkInsResult.messageResId)
            AsyncResult.Loading -> LoadingAnimation()
            is AsyncResult.Success -> {
                LazyColumn(contentPadding = PaddingValues(4.dp)) {
                    items(checkInsResult.data) {
                        CheckInCard(checkIn = it)
                    }
                }
            }
        }

        Button(onClick = {
            //TODO: Démarrer la bibliothèque de scan
        }) {
            Text(text = stringResource(R.string.check_in))
        }
    }


}

@Composable
fun CheckInCard(checkIn: CheckIn) {

    ElevatedCard(modifier = Modifier.padding(bottom = 4.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = checkIn.door.toString(), style = MaterialTheme.typography.headlineMedium)
                Text(text = checkIn.scanId, style = MaterialTheme.typography.headlineMedium)
            }
            Text(text = checkIn.scannedDate)
        }
    }

}