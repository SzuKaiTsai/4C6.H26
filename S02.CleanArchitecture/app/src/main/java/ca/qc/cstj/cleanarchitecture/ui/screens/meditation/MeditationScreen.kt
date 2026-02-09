package ca.qc.cstj.cleanarchitecture.ui.screens.meditation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.cleanarchitecture.R
import ca.qc.cstj.cleanarchitecture.core.colorPaths
import ca.qc.cstj.cleanarchitecture.data.MockData
import ca.qc.cstj.cleanarchitecture.models.MeditationSession
import ca.qc.cstj.cleanarchitecture.ui.theme.Green3
import ca.qc.cstj.cleanarchitecture.ui.theme.Pink3
import ca.qc.cstj.cleanarchitecture.ui.theme.featureCardTextColor
import ca.qc.cstj.cleanarchitecture.ui.theme.featureCardTextStyle

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
            Text(text = pluralStringResource(
                R.plurals.format_minutes,
                meditationSession.durationInMinutes,
                meditationSession.durationInMinutes))
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
            FeatureMeditationCard(it)
        }
    }
}

@Composable
fun FeatureMeditationCard(meditationSession: MeditationSession){
    Card(
        modifier = Modifier.height(80.dp),
        colors = CardDefaults.cardColors(meditationSession.backgroundColor)
    ){
        Row(modifier =
            Modifier
                .padding(8.dp)
                .fillMaxSize()
                .drawBehind{
                    val (mediumColorPath, lightColorPath) = colorPaths(size.width, size.height)
                    drawPath(path = mediumColorPath, color = meditationSession.colors.second)
                    drawPath(path = lightColorPath, color = meditationSession.colors.third)
                },

            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                Arrangement.SpaceBetween
            ) {
                Text(text = meditationSession.title, style = featureCardTextStyle)
                Row(
                    modifier = Modifier,
                    Arrangement.spacedBy(8.dp),
                    Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(meditationSession.icon),
                        contentDescription = meditationSession.category.toString(),
                        tint = featureCardTextColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(text = pluralStringResource(
                        R.plurals.format_minutes,
                        meditationSession.durationInMinutes,
                         meditationSession.durationInMinutes),
                        style = featureCardTextStyle
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.play),
                    contentDescription = "Play",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(42.dp)

                )
            }
        }
    }
}

@Preview
@Composable
fun MeditationScreenPreview(){
    CurrentMeditationSession(MockData.meditationSessions.random())
}