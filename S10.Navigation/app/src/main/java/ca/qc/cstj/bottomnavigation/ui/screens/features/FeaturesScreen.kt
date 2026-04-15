package ca.qc.cstj.bottomnavigation.ui.screens.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.ui.navigation.Feature
import ca.qc.cstj.bottomnavigation.ui.screens.main.MainAction
import ca.qc.cstj.bottomnavigation.ui.screens.main.MainEvent


@Composable
fun FeatureScreen(
    viewModel: FeaturesViewModel = viewModel(),
    onMainScreenAction: (MainAction) -> Unit,
    onMainScreenEvent: (MainEvent) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        onMainScreenAction(MainAction.UpdateTopBarOptions(Feature.topBarOptions.copy(titleArgs = listOf("Felix"))))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(text = stringResource(R.string.features))

        val snackBarMessage = stringResource(R.string.bonjour_etc)
        Button(
            onClick = {
                onMainScreenEvent(MainEvent.ShowSnackbar(snackBarMessage,"Miam"))
            }
        ) {
            Text(text = stringResource(R.string.snackbar))
        }

        if (uiState.isFinished){
            Text(text="end of timer", color = Color.Magenta, fontSize = 32.sp)
        } else {
            Text(text = uiState.progression.toString(), color = Color.Magenta, fontSize = 32.sp)
        }
    }

}
