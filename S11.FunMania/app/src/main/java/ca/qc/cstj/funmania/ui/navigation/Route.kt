package ca.qc.cstj.funmania.ui.navigation

import com.google.android.gms.maps.model.LatLng

sealed interface Route {
    data object ToTitleScreen: Route
    data object ToMainScreen: Route
    data class ToMap(val latLng: LatLng):Route
}