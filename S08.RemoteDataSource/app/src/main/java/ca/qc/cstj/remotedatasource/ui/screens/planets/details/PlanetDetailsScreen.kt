package ca.qc.cstj.remotedatasource.ui.screens.planets.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.remotedatasource.R
import ca.qc.cstj.remotedatasource.core.AsyncResult
import ca.qc.cstj.remotedatasource.core.ui.ErrorMessage
import ca.qc.cstj.remotedatasource.core.ui.LoadingAnimation
import ca.qc.cstj.remotedatasource.model.Planet
import ca.qc.cstj.remotedatasource.ui.components.PlanetCard
import ca.qc.cstj.remotedatasource.ui.components.PortalCard


@Composable
fun PlanetDetailsScreen(
    href: String,
    viewModel: PlanetDetailsViewModel = viewModel{
        PlanetDetailsViewModel(href)
    }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(val result = uiState.planetResult) {
        is AsyncResult.Error -> ErrorMessage(result.messageResId)
        AsyncResult.Loading -> LoadingAnimation()
        is AsyncResult.Success -> PlanetDetailContent(result.data)
    }
}

@Composable
fun PlanetDetailContent(planet: Planet) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PlanetCard(planet)
        Text(text = stringResource(R.string.portals), style = MaterialTheme.typography.headlineLarge)
        LazyColumn() {
            items(planet.portals){
                PortalCard(it)
            }
        }
    }
}