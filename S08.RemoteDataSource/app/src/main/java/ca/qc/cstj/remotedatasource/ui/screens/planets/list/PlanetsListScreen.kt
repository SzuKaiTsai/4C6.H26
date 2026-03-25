package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.remotedatasource.core.AsyncResult
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.core.ui.ErrorMessage
import ca.qc.cstj.remotedatasource.core.ui.LoadingAnimation
import ca.qc.cstj.remotedatasource.model.Planet
import ca.qc.cstj.remotedatasource.ui.components.PlanetCard
import ca.qc.cstj.remotedatasource.ui.theme.RemoteDataSourceTheme


@Composable
fun PlanetsListScreen(
    viewModel: PlanetsListViewModel = viewModel(), toPlanetDetailScreen: (Planet) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    PlanetsListContent(
        uiState, onAction = viewModel::onAction, toPlanetDetailScreen
    )
}

@Composable
fun PlanetsListContent(
    uiState: PlanetsListUiState,
    onAction: (PlanetsListAction) -> Unit,
    toPlanetDetailScreen: (Planet) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilterChip(
                selected = uiState.temperatureUnit == Constants.TemperatureUnit.Celsius,
                onClick = { onAction(PlanetsListAction.ChangeTemperatureUnit(Constants.TemperatureUnit.Celsius)) },
                label = { Text(text = "Celsius") })
            FilterChip(
                selected = uiState.temperatureUnit == Constants.TemperatureUnit.Fahrenheit,
                onClick = { onAction(PlanetsListAction.ChangeTemperatureUnit(Constants.TemperatureUnit.Fahrenheit)) },
                label = { Text(text = "Fahrenheit ") })
            FilterChip(
                selected = uiState.temperatureUnit == Constants.TemperatureUnit.Kelvin,
                onClick = { onAction(PlanetsListAction.ChangeTemperatureUnit(Constants.TemperatureUnit.Kelvin)) },
                label = { Text(text = "Kelvin ") })
        }

        AnimatedVisibility(visible = uiState.isRefreshing) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary
            )
        }

        PullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            isRefreshing = uiState.isRefreshing,
            onRefresh = { onAction(PlanetsListAction.RefreshPlanet)}
        ) {
            when(uiState.planetResult) {
            is AsyncResult.Error -> ErrorMessage(uiState.planetResult.messageResId)
            AsyncResult.Loading -> LoadingAnimation()
            is AsyncResult.Success -> {
                LazyColumn {
                    items(uiState.planetResult.data){
                        PlanetCard(
                            planet = it,
                            unit = uiState.temperatureUnit,
                            onClick = {}
                        )

                    }
                }
            }
        }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    RemoteDataSourceTheme {
        PlanetsListContent(uiState = PlanetsListUiState(), onAction = {}, toPlanetDetailScreen = {})
    }
}