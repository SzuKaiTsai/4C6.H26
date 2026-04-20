package ca.qc.cstj.funmania.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ca.qc.cstj.funmania.core.extensions.ObserveAsEvents
import ca.qc.cstj.funmania.core.ui.navigation.Screen
import ca.qc.cstj.funmania.ui.navigation.Barcode
import ca.qc.cstj.funmania.ui.navigation.MainNavigationBar
import ca.qc.cstj.funmania.ui.navigation.MainTopBar
import ca.qc.cstj.funmania.ui.navigation.Orientation
import ca.qc.cstj.funmania.ui.navigation.Weather
import ca.qc.cstj.funmania.ui.screens.barcode.BarcodeScreen
import ca.qc.cstj.funmania.ui.screens.orientation.OrientationScreen
import ca.qc.cstj.funmania.ui.screens.weather.WeatherScreen
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    toMapScreen: (LatLng) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.topLevelBackStack.currentKey) {
        val currentScreen = uiState.topLevelBackStack.currentKey as Screen
        viewModel.onAction(MainAction.UpdateTopBarOptions(currentScreen.topBarOptions))
    }

    @SuppressLint("LocalContextGetResourceValueCall")
    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is MainEvent.ShowSnackbar -> {
                val message = context.getString(event.messageId)
                val event = event.actionId?.let { context.getString(it) }
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = message,
                        actionLabel = event
                    )
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if(uiState.topBarOptions.isTopBarVisible) {
                MainTopBar(
                    topBarOptions = uiState.topBarOptions,
                    onNavigateBack = { uiState.topLevelBackStack.removeLast()}
                )
            }
        },
        bottomBar = {
            val currentScreen = uiState.topLevelBackStack.currentKey as Screen
            if(currentScreen.bottomBarOptions.isBottomBarVisible) {
                MainNavigationBar(
                    bottomBarOptions = uiState.bottomBarOptions,
                    topLevelBackStack = uiState.topLevelBackStack
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState, modifier = Modifier.systemBarsPadding()
            ) {
                Snackbar(snackbarData = it)
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            backStack = uiState.topLevelBackStack.backStack,
            onBack = { uiState.topLevelBackStack.removeLast() },
            entryProvider = entryProvider {
                entry<Weather> {
                    WeatherScreen(
                        toMapScreen = { toMapScreen(it) }
                    )
                }
                entry<Barcode> {
                    BarcodeScreen()
                }
                entry<Orientation> {
                    OrientationScreen()
                }
            }
        )

    }
}
