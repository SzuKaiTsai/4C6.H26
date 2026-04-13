package ca.qc.cstj.bottomnavigation.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
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
import ca.qc.cstj.bottomnavigation.ui.navigation.Home
import ca.qc.cstj.bottomnavigation.ui.navigation.One
import ca.qc.cstj.bottomnavigation.ui.navigation.Three
import ca.qc.cstj.bottomnavigation.ui.navigation.Two
import ca.qc.cstj.bottomnavigation.ui.navigation.components.MainNavigationBar
import ca.qc.cstj.bottomnavigation.ui.screens.contents.HomeScreen
import ca.qc.cstj.bottomnavigation.ui.screens.contents.OneScreen
import ca.qc.cstj.bottomnavigation.ui.screens.contents.ThreeScreen
import ca.qc.cstj.bottomnavigation.ui.screens.contents.TwoScreen

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


    ObserveAsEvents(viewModel.events) { event -> //TODO:
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            //TODO:
        },
        bottomBar = {
            MainNavigationBar(
                bottomBarOptions = uiState.bottomBarOptions,
                topLevelBackStack = topLevelBackStack
            )
        },
        snackbarHost = {
            //TODO:
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
                entry<One> {
                    OneScreen(onDetailClick = {})
                }
                entry<Two> {
                    TwoScreen()
                }
                entry<Three> {
                    ThreeScreen()
                }
            }
        )
    }
}
