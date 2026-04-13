package ca.qc.cstj.bottomnavigation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation3.runtime.NavKey
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.ui.navigation.BottomNavItem

data object Home: BottomNavItem{
    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.outline_kayaking_24)
    override val title: String
        @Composable get() = stringResource(R.string.home)
}

data object One: BottomNavItem{
    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.looks_one)
    override val title: String
        @Composable get() = stringResource(R.string.one)
}

data object Two: BottomNavItem{
    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.looks_two)
    override val title: String
        @Composable get() = stringResource(R.string.two)
}

data object Three: BottomNavItem{
    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.looks_three)
    override val title: String
        @Composable get() = stringResource(R.string.three)
}