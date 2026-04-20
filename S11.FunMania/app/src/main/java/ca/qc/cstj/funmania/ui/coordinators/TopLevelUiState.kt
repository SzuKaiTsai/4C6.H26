package ca.qc.cstj.funmania.ui.coordinators

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import ca.qc.cstj.funmania.ui.navigation.Route

data class TopLevelUiState(
    val backStack : SnapshotStateList<Route> =  mutableStateListOf<Route>(Route.ToTitleScreen)
)