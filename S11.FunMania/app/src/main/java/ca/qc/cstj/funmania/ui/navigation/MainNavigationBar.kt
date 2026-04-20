package ca.qc.cstj.funmania.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import ca.qc.cstj.funmania.core.ui.navigation.BottomBarOptions
import ca.qc.cstj.funmania.core.ui.navigation.TopLevelBackStack

@Composable
fun MainNavigationBar(
    bottomBarOptions: BottomBarOptions,
    topLevelBackStack: TopLevelBackStack<NavKey>
) {

    NavigationBar {
        bottomBarOptions.items.forEach { item ->
            val selected = topLevelBackStack.topLevelKey == item
            NavigationBarItem(
                selected = selected,
                onClick = {
                    topLevelBackStack.switchTopLevel(item)
                },
                icon = {
                    Icon(
                        imageVector = item.icon, contentDescription = item.title
                    )
                },
                label = {
                    Text(item.title)
                },
            )
        }
    }


}