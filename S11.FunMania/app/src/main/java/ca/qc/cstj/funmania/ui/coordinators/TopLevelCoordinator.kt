package ca.qc.cstj.funmania.ui.coordinators

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ca.qc.cstj.funmania.ui.navigation.Route
import ca.qc.cstj.funmania.ui.screens.main.MainScreen
import ca.qc.cstj.funmania.ui.screens.map.MapScreen
import ca.qc.cstj.funmania.ui.screens.title.TitleScreen

@Composable
fun TopLevelCoordinator(
    viewModel: TopLevelViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val backStack = uiState.backStack

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        transitionSpec = { slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut() },
        popTransitionSpec = { slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut() },
        predictivePopTransitionSpec = { slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut() },
        entryProvider = entryProvider {
            entry<Route.ToMainScreen> {
                MainScreen(
                    toMapScreen = { latLng ->  backStack.add(Route.ToMap(latLng)) }
                )
            }
            entry<Route.ToMap> { args ->
                MapScreen(args.latLng)
            }
            entry<Route.ToTitleScreen> {
                TitleScreen { backStack.add(Route.ToMainScreen) }
            }
        })
}