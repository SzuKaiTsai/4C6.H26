package ca.qc.cstj.bottomnavigation.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import ca.qc.cstj.bottomnavigation.core.extensions.ObserveAsEvents
import ca.qc.cstj.bottomnavigation.core.ui.navigation.Screen
import ca.qc.cstj.bottomnavigation.ui.navigation.DetailOne
import ca.qc.cstj.bottomnavigation.ui.navigation.Feature
import ca.qc.cstj.bottomnavigation.ui.navigation.Home
import ca.qc.cstj.bottomnavigation.ui.navigation.One
import ca.qc.cstj.bottomnavigation.ui.navigation.Three
import ca.qc.cstj.bottomnavigation.ui.navigation.Two
import ca.qc.cstj.bottomnavigation.ui.navigation.components.MainNavigationBar
import ca.qc.cstj.bottomnavigation.ui.navigation.components.MainTopBar
import ca.qc.cstj.bottomnavigation.ui.screens.contents.DetailOneScreen
import ca.qc.cstj.bottomnavigation.ui.screens.contents.HomeScreen
import ca.qc.cstj.bottomnavigation.ui.screens.contents.OneScreen
import ca.qc.cstj.bottomnavigation.ui.screens.contents.ThreeScreen
import ca.qc.cstj.bottomnavigation.ui.screens.contents.TwoScreen
import ca.qc.cstj.bottomnavigation.ui.screens.features.FeatureScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    //TODO: topLevelBackStack
    val topLevelBackStack = uiState.topLevelBackStack

    //TODO: UpdateTopBar
    LaunchedEffect(topLevelBackStack.currentKey) {
        val currentScreen = topLevelBackStack.currentKey as Screen
        viewModel.onAction(MainAction.UpdateTopBarOptions(currentScreen.topBarOptions))
    }

    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is MainEvent.ShowSnackbar -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.actionLabel,
                    )
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if(uiState.topBarOptions.isTopBarVisible){
                MainTopBar(
                    topBarOptions =  uiState.topBarOptions,
                    onNavigateBack = { topLevelBackStack.removeLast()}
                )
            }

        },
        bottomBar = {
            val currentScreen = topLevelBackStack.currentKey as Screen
            if(currentScreen.bottomBarOptions.isBottomBarVisible){
                MainNavigationBar(
                    bottomBarOptions = uiState.bottomBarOptions,
                    topLevelBackStack = topLevelBackStack
                )
            }

        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            ) {
                Snackbar(snackbarData = it)
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 4.dp)
                .fillMaxSize(),
            backStack = topLevelBackStack.backStack,
            onBack = { topLevelBackStack.removeLast() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Home>{
                    HomeScreen()
                }
                entry<Feature> {
                    FeatureScreen(
                        onMainScreenAction={viewModel.onAction(it)},
                        onMainScreenEvent={viewModel.onEvent(it)}
                    )
                }
                entry<One> {
                    OneScreen(onDetailClick = {
                        topLevelBackStack.add(DetailOne("https:..."))
                    })
                }
                entry<Two> {
                    TwoScreen()
                }
                entry<Three> {
                    ThreeScreen()
                }
                entry<DetailOne>{
                    DetailOneScreen(it.href)
                }
            }
        )
    }
}
