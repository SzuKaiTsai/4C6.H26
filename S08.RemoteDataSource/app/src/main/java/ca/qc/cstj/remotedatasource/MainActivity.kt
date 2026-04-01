package ca.qc.cstj.remotedatasource

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import ca.qc.cstj.remotedatasource.ui.navigation.Route
import ca.qc.cstj.remotedatasource.ui.screens.planets.details.PlanetDetailsScreen
import ca.qc.cstj.remotedatasource.ui.screens.planets.list.PlanetsListScreen
import ca.qc.cstj.remotedatasource.ui.theme.RemoteDataSourceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RemoteDataSourceTheme(dynamicColor = false) {
                val backStack = remember { mutableStateListOf<Route>(Route.PlanetList) }
                Scaffold { innerPaddings ->
                    NavDisplay(
                        modifier = Modifier.fillMaxSize().padding(innerPaddings).padding(horizontal = 4.dp),
                        backStack = backStack,
                        transitionSpec = { slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut() },
                        popTransitionSpec = { slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut() },
                        predictivePopTransitionSpec = { slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut() },
                        entryDecorators = listOf(
                            rememberSaveableStateHolderNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator()
                        ),
                        entryProvider = entryProvider {
                            entry<Route.PlanetList> {
                                PlanetsListScreen(
                                    toPlanetDetailScreen = {
                                        backStack.add(Route.PlanetDetail(it.href))
                                    }
                                )
                            }
                            entry<Route.PlanetDetail> {
                                PlanetDetailsScreen(
                                    href = it.href
                                )
                            }

                        })
                }
            }
        }
    }
}
