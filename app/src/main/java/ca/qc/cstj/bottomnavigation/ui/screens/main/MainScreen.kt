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

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    //TODO: topLevelBackStack

    //TODO: UpdateTopBar

    ObserveAsEvents(viewModel.events) { event -> //TODO:
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            //TODO:
        },
        bottomBar = {
            //TODO:
        },
        snackbarHost = {
            //TODO:
        }
    ) { innerPadding ->
//        NavDisplay(
//            modifier = Modifier
//                .padding(innerPadding)
//                .padding(horizontal = 4.dp)
//                .fillMaxSize(),
//            backStack = mutableListOf(), //TODO:
//            onBack = { },
//            entryDecorators = listOf(
//                rememberSaveableStateHolderNavEntryDecorator(),
//                rememberViewModelStoreNavEntryDecorator()
//            ),
//            entryProvider = entryProvider {
//                //TODO:
//            }
//        )
    }
}
