package ca.qc.cstj.cleanarchitecture.ui.screens.meditation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.cleanarchitecture.R
import ca.qc.cstj.cleanarchitecture.data.MockData
import ca.qc.cstj.cleanarchitecture.models.MeditationSession
import ca.qc.cstj.cleanarchitecture.ui.theme.Green3

@Composable
fun MeditationScreen(viewModel: MeditationViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentMeditationSession(uiState.currentMeditationSession)
        FeaturesGrid(uiState.featuresMeditationSession)
    }
}

// barre de recherche

// filtre par tag (etiquette)

// meditation courante
@Composable
fun CurrentMeditationSession(meditationSession: MeditationSession){

    Card(colors = CardDefaults.cardColors(
        containerColor = Green3
    )) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(text = meditationSession.title, fontSize = 24.sp)
            Text(text = stringResource(R.string.format_minutes, meditationSession.durationMinutes))
        }

    }

}
// meditations recommendees
@Composable
fun FeaturesGrid(meditationSessions: List<MeditationSession>){

    //Affichage d'une collection
    LazyVerticalGrid(
        modifier = Modifier.fillMaxHeight(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(meditationSessions){
            CurrentMeditationSession(it)
        }
    }
}

@Preview
@Composable
fun MeditationScreenPreview(){
    CurrentMeditationSession(MockData.meditationSessions.random())
}