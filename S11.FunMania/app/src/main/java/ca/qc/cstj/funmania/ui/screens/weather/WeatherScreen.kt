package ca.qc.cstj.funmania.ui.screens.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.funmania.R
import ca.qc.cstj.funmania.core.AsyncAction
import ca.qc.cstj.funmania.core.Constants
import ca.qc.cstj.funmania.core.extensions.format
import ca.qc.cstj.funmania.core.extensions.toLocalDateTimeFormat
import ca.qc.cstj.funmania.core.ui.components.ErrorMessage
import ca.qc.cstj.funmania.core.ui.components.LoadingAnimation
import ca.qc.cstj.funmania.data.datasources.WeatherType
import ca.qc.cstj.funmania.models.WeatherInfo
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.LatLng

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel(),
    toMapScreen: (LatLng) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchField(
            searchText = uiState.search,
            onChangeSearchText = { viewModel.onAction(WeatherAction.OnUpdateSearchText(it)) },
            onSearchClick = { viewModel.onAction(WeatherAction.OnSearch) }
        )

        when (val currentWeatherResult = uiState.currentWeatherResult) {
            is AsyncAction.Error -> {
                ErrorMessage(currentWeatherResult.messageResId)
            }
            AsyncAction.Loading -> {
                LoadingAnimation()
            }
            is AsyncAction.Success -> {
                CurrentWeatherSection(weatherInfo = currentWeatherResult.data) {
                    val latLng = LatLng(currentWeatherResult.data.latitude, currentWeatherResult.data.longitude)
                    toMapScreen(latLng)
                }
            }
            AsyncAction.Idle -> { }
        }

    }
}


@Composable
fun CurrentWeatherSection(weatherInfo: WeatherInfo, onMapClick:() -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.6f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = weatherInfo.city,
                style = MaterialTheme.typography.headlineMedium
            )
            AsyncImage(
                modifier = Modifier.size(64.dp),
                model = Constants.NetworkEndPoint.FLAGS_API_URL.format(weatherInfo.country),
                contentDescription = weatherInfo.country
            )
            Text(
                text = weatherInfo.locationDateTime.format(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = weatherInfo.systemDefaultDate.toLocalDateTimeFormat(),
                style = MaterialTheme.typography.bodyMedium
            )
            WeatherImage(weatherInfo = weatherInfo)
            Text(
                text = stringResource(
                    R.string.temperature_value_in_celsius, weatherInfo.temperature.toString()
                ),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                text = weatherInfo.weather,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp), text = stringResource(
                    R.string.feels_like_temperature_in_celsius, weatherInfo.feelsLike.toString()
                ), style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = stringResource(id = R.string.geographic_position, weatherInfo.latitude, weatherInfo.longitude),
                style = MaterialTheme.typography.bodySmall
            )
            IconButton(onClick = {
                onMapClick()
            }) {
                Icon(
                    imageVector = Icons.Default.GpsFixed, contentDescription = null
                )
            }

        }
    }
}

@Composable
private fun SearchField(
    searchText: String, onChangeSearchText: (String) -> Unit, onSearchClick: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 16.dp),
        value = searchText,
        onValueChange = { onChangeSearchText(it) },
        label = {
            Text(text = stringResource(R.string.search_for_city))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            }
        }
    )
}

@Composable
fun WeatherImage(weatherInfo: WeatherInfo) {

    val weatherType = WeatherType.factory(
        weatherInfo.weather,
        weatherInfo.description,
        weatherInfo.systemDefaultDate)

    Image(
        modifier = Modifier.size(128.dp),
        painter = painterResource(id = weatherType.id),
        contentDescription = "${weatherInfo.weather} - ${weatherInfo.description}"
    )
}