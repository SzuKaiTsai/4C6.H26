package ca.qc.cstj.funmania.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ca.qc.cstj.funmania.R
import ca.qc.cstj.funmania.core.ui.navigation.BottomNavItem
import ca.qc.cstj.funmania.core.ui.navigation.Screen


data object Weather: BottomNavItem, Screen {

    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.outline_sunny_24)

    override val title: String
        @Composable get() = stringResource(R.string.weather)

}

data object Barcode: BottomNavItem, Screen {

    override val icon: ImageVector
        @Composable get() = ImageVector.vectorResource(R.drawable.outline_barcode_scanner_24)

    override val title: String
        @Composable get() = stringResource(R.string.barcode)

}

data object Orientation: BottomNavItem, Screen {

    override val icon: ImageVector
        @Composable get() = Icons.Default.Landscape

    override val title: String
        @Composable get() = stringResource(R.string.orientation)

}
