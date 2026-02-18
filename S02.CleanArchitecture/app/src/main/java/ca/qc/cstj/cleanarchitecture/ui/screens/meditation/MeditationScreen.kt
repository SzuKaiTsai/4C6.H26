package ca.qc.cstj.cleanarchitecture.ui.screens.meditation

import android.R.attr.tag
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.cleanarchitecture.R
import ca.qc.cstj.cleanarchitecture.core.colorPaths
import ca.qc.cstj.cleanarchitecture.data.MockData
import ca.qc.cstj.cleanarchitecture.models.MeditationSession
import ca.qc.cstj.cleanarchitecture.ui.navigation.Route
import ca.qc.cstj.cleanarchitecture.ui.theme.Green3
import ca.qc.cstj.cleanarchitecture.ui.theme.Pink3
import ca.qc.cstj.cleanarchitecture.ui.theme.currentMeditationCardTextStyle
import ca.qc.cstj.cleanarchitecture.ui.theme.featureCardTextColor
import ca.qc.cstj.cleanarchitecture.ui.theme.featureCardTextStyle

@Composable
fun MeditationScreen(
    route: Route.MeditationRoute,
    viewModel: MeditationViewModel = viewModel(){
        MeditationViewModel(route.name)
}) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(route.name) {
        viewModel.onAction(MeditationAction.OnNameChange(route.name))
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        WelcomeMessage(uiState.name)
        SearchBar(
            searchText = uiState.searchText,
            onSearch = { viewModel.onAction(MeditationAction.OnSearch(it)) }
        )
        TagsFilter(
            tags = uiState.tags,
            filterTag=uiState.tagFilter,
            onTagClick={
                tag->viewModel.onAction(MeditationAction.OnTagClicked(tag))
            }
        )
        CurrentMeditationSession(uiState.currentMeditationSession)
        FeaturesGrid(
            uiState.featuresMeditationSession,
                    onStartClick = {
                        meditationSession -> viewModel.onAction(
                        MeditationAction.OnStartMeditationClicked(meditationSession)
                        )}
        )
    }
}

//
@Composable
fun WelcomeMessage(nom:String){
    Text(text = stringResource(R.string.good_morning,nom))
    Text(text= stringResource(R.string.we_wish_you_a_good_day), style = MaterialTheme.typography.bodySmall)
}

// barre de recherche
@Composable
fun SearchBar(searchText: String, onSearch: (String) -> Unit){
    TextField(modifier =
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp, color = LocalTextStyle.current.color, shape = RoundedCornerShape(8.dp)
            ),
        value = searchText,
        onValueChange = { onSearch(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search)
            )
        },
        placeholder = {
            Text(stringResource(R.string.search), fontWeight = FontWeight.SemiBold)
        }

    )
}


// filtre par tag (etiquette)

@Composable
fun TagsFilter(tags: List<String>, filterTag: MeditationFilter, onTagClick: (MeditationFilter)-> Unit){
    LazyRow(
        modifier = Modifier.padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

        // un item - pas de boucle
        item{
            FilterChip(
                label = { Text(text = stringResource(R.string.all), style = MaterialTheme.typography.bodyMedium)},
                 selected = filterTag is MeditationFilter.All,
                onClick = {
                    onTagClick(MeditationFilter.All)
                },
                border = FilterChipDefaults.filterChipBorder(selected = false, enabled = true)
            )
        }

        items(tags){
            FilterChip(label = { Text(text = it, style = MaterialTheme.typography.bodyMedium)},
            selected = when(filterTag){
                MeditationFilter.All -> false
                is MeditationFilter.ByTag -> filterTag.tag == it
            },
                onClick = {
                    onTagClick(MeditationFilter.ByTag(it))
                },
                border = FilterChipDefaults.filterChipBorder(selected = false, enabled = true)
            )
        }
    }
}

// meditations recommendees
@Composable
fun FeaturesGrid(
    meditationSessions: List<MeditationSession>,
    onStartClick: (MeditationSession) -> Unit
    ){

    Text(text= stringResource(R.string.features), style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(vertical = 8.dp))
    //Affichage d'une collection
    LazyVerticalGrid(
        modifier = Modifier.fillMaxHeight(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(meditationSessions){
            FeatureMeditationCard(it, onStartClick)
        }
    }
}

@Composable
fun FeatureMeditationCard(
    meditationSession: MeditationSession, onStartClick: (MeditationSession) -> Unit
){
    Card(
        modifier = Modifier.height(80.dp),
        colors = CardDefaults.cardColors(meditationSession.backgroundColor)
    ){
        Row(modifier =
            Modifier
                .padding(8.dp)
                .fillMaxSize()
                .drawBehind {
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
            FilledIconButton(
                onClick = { onStartClick(meditationSession) },
                modifier = Modifier.size(40.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// meditation courante
@Composable
fun CurrentMeditationSession(meditationSession: MeditationSession) {
    Card(colors = CardDefaults.cardColors(meditationSession.colors.first)) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .drawBehind {
                    val (mediumColorPath, lightColorPath) = colorPaths(size.width, size.height)
                    drawPath(
                        path = mediumColorPath, color = meditationSession.colors.second
                    )
                    drawPath(
                        path = lightColorPath, color = meditationSession.colors.third
                    )
                }, verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = meditationSession.title,
                    style = currentMeditationCardTextStyle
                )
                Icon(
                    painter = painterResource(meditationSession.icon),
                    contentDescription = meditationSession.category.toString(),
                    tint = featureCardTextColor,
                    modifier = Modifier
                        .size(32.dp)
                        .offset(y = (-8).dp)
                )
            }

            Text(
                text = stringResource(R.string.with, meditationSession.with),
                style = featureCardTextStyle
            )

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = meditationSession.subtitle, style = featureCardTextStyle
                )
                Text(
                    text = pluralStringResource(
                        R.plurals.format_minutes,
                        meditationSession.durationInMinutes,
                        meditationSession.durationInMinutes
                    ), style = featureCardTextStyle
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