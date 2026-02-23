package ca.qc.cstj.inkify.ui.navigation

sealed interface Route {
    data object AddNoteRoute: Route
}