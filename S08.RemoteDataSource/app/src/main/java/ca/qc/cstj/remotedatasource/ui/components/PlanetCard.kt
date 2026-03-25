package ca.qc.cstj.remotedatasource.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.qc.cstj.remotedatasource.R
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.model.Planet
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PlanetCard(
    planet: Planet,
    unit: Constants.TemperatureUnit = Constants.TemperatureUnit.Celsius,
    onClick : (Planet) -> Unit = {}
) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onClick(planet)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(0.4f),
                model = ImageRequest.Builder(LocalContext.current).data(planet.icon).build(),
                contentDescription = planet.name,
                contentScale = ContentScale.Fit
            )

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = planet.name, style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = when(unit) {
                        Constants.TemperatureUnit.Kelvin -> stringResource(id = R.string.kelvin_format, planet.temperature)
                        Constants.TemperatureUnit.Celsius -> stringResource(id = R.string.celsius_format, planet.temperature)
                        Constants.TemperatureUnit.Fahrenheit -> stringResource(id = R.string.fahrenheit_format, planet.temperature)
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }

}