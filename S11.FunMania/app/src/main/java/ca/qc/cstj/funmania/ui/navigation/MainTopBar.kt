package ca.qc.cstj.funmania.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import ca.qc.cstj.funmania.core.ui.navigation.TopBarOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    topBarOptions: TopBarOptions,
    onNavigateBack: (() -> Unit)? = null,
    toSettingsScreen: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(text = topBarOptions.title)
        },
        navigationIcon = {
            if (onNavigateBack != null && topBarOptions.isBackButtonVisible) {
                IconButton(onClick = { onNavigateBack() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack, null
                    )
                }
        }
    }, actions = {
        if (toSettingsScreen != null) {
            IconButton(onClick = {
                toSettingsScreen()
            }) {
                Icon(
                    imageVector = Icons.Filled.Settings, contentDescription = null
                )
            }
        }
    })
}