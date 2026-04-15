package ca.qc.cstj.bottomnavigation.ui.screens.main

import androidx.navigation3.runtime.NavKey
import ca.qc.cstj.bottomnavigation.core.ui.navigation.BottomBarOptions
import ca.qc.cstj.bottomnavigation.core.ui.navigation.TopBarOptions
import ca.qc.cstj.bottomnavigation.core.ui.navigation.TopLevelBackStack
import ca.qc.cstj.bottomnavigation.ui.navigation.Feature
import ca.qc.cstj.bottomnavigation.ui.navigation.Home
import ca.qc.cstj.bottomnavigation.ui.navigation.One
import ca.qc.cstj.bottomnavigation.ui.navigation.Three
import ca.qc.cstj.bottomnavigation.ui.navigation.Two

data class MainUiState(
    val bottomBarOptions: BottomBarOptions = BottomBarOptions(
        items = listOf(Home, Feature, One, Two, Three)
    ),
    val topLevelBackStack: TopLevelBackStack<NavKey> = TopLevelBackStack(Home),
    val topBarOptions: TopBarOptions = TopBarOptions.Defaults
)
