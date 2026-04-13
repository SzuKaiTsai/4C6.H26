package ca.qc.cstj.bottomnavigation.ui.screens.main

import ca.qc.cstj.bottomnavigation.core.ui.navigation.TopBarOptions

sealed interface MainAction {
    data class UpdateTopBarOptions(val topBarOptions: TopBarOptions): MainAction
}

sealed interface MainEvent {
    data class ShowSnackbar(val message: String, val actionLabel: String) : MainEvent
}
