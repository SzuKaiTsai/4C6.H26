package ca.qc.cstj.funmania.ui.screens.main

import androidx.navigation3.runtime.NavKey
import ca.qc.cstj.funmania.core.ui.navigation.BottomBarOptions
import ca.qc.cstj.funmania.core.ui.navigation.TopBarOptions
import ca.qc.cstj.funmania.core.ui.navigation.TopLevelBackStack
import ca.qc.cstj.funmania.ui.navigation.Barcode
import ca.qc.cstj.funmania.ui.navigation.Orientation
import ca.qc.cstj.funmania.ui.navigation.Weather

data class MainUiState(
    val topLevelBackStack: TopLevelBackStack<NavKey> = TopLevelBackStack<NavKey>(Weather),
    val topBarOptions : TopBarOptions = TopBarOptions.Defaults,
    val bottomBarOptions: BottomBarOptions = BottomBarOptions(items = listOf(Weather, Barcode, Orientation))
)
