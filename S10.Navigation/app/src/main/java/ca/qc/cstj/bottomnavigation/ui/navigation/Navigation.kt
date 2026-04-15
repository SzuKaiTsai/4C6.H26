package ca.qc.cstj.bottomnavigation.ui.navigation

import androidx.navigation3.runtime.NavKey
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.ui.navigation.BottomBarOptions
import ca.qc.cstj.bottomnavigation.core.ui.navigation.Screen
import ca.qc.cstj.bottomnavigation.core.ui.navigation.TopBarOptions

data class DetailOne(val href:String) : NavKey, Screen {
    override val topBarOptions= TopBarOptions(isBackButtonVisible = true, titleStringRes = R.string.one)
    override val bottomBarOptions = BottomBarOptions(isBottomBarVisible = false)
}