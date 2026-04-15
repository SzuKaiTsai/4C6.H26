package ca.qc.cstj.bottomnavigation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.ui.navigation.BottomNavItem
import ca.qc.cstj.bottomnavigation.core.ui.navigation.Screen
import ca.qc.cstj.bottomnavigation.core.ui.navigation.TopBarOptions


data object Home: BottomNavItem, Screen {
    override val topBarOptions = TopBarOptions(titleStringRes = R.string.home)
    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.outline_kayaking_24)
    override val title: String
        @Composable get() = stringResource(R.string.home)
}

data object One: BottomNavItem, Screen {
    override val topBarOptions = TopBarOptions(titleStringRes = R.string.one)
    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.looks_three)
    override val title: String
        @Composable get() = stringResource(R.string.one)
}

data object Feature: BottomNavItem, Screen{
    override val topBarOptions = TopBarOptions(titleStringRes = R.string.features_topbar)
    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.outline_local_pizza_24)
    override val title: String
        @Composable get() = stringResource(R.string.features)
}

data object Two: BottomNavItem, Screen{
    override val topBarOptions = TopBarOptions(titleStringRes = R.string.two)
    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.looks_one)
    override val title: String
        @Composable get() = stringResource(R.string.two)
}

data object Three: BottomNavItem, Screen{
    override val topBarOptions = TopBarOptions(titleStringRes = R.string.three)
    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.looks_two)
    override val title: String
        @Composable get() = stringResource(R.string.three)
}