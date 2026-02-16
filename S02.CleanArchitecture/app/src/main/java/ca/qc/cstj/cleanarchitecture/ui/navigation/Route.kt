package ca.qc.cstj.cleanarchitecture.ui.navigation

sealed interface Route {
    data object TitleRoute: Route
    data object MeditationRoute: Route
}