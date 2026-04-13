package ca.qc.cstj.bottomnavigation.ui.navigation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import ca.qc.cstj.bottomnavigation.core.ui.navigation.BottomBarOptions
import ca.qc.cstj.bottomnavigation.core.ui.navigation.TopLevelBackStack

@Composable
fun MainNavigationBar(
    bottomBarOptions: BottomBarOptions,
    topLevelBackStack: TopLevelBackStack<NavKey>
) {
    NavigationBar() {
        bottomBarOptions.items.forEach {
            NavigationBarItem(
                selected = topLevelBackStack.topLevelKey == it,
                onClick = {
                    topLevelBackStack.switchTopLevel(it)
                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                }
            )
        }
    }
}