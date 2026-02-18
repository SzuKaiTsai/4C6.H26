package ca.qc.cstj.cleanarchitecture.ui.navigation

sealed interface Route {
    data object TitleRoute: Route
    data class MeditationRoute(val name: String): Route
}