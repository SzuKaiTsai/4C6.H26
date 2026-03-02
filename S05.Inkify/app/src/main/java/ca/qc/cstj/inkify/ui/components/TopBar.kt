package ca.qc.cstj.inkify.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ca.qc.cstj.inkify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InkifyTopBar(
    title: String = stringResource(R.string.app_name),
    onNavigateBack: (() -> Unit)? = null,
    toSettingsScreen: (() -> Unit)? = null
){
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if(onNavigateBack != null){
            IconButton(
                onClick = {onNavigateBack()}
            )  {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name
                )
            }

            }
        },
        actions = {
            if (toSettingsScreen != null){
            IconButton(
                onClick = {
                    toSettingsScreen()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = Icons.Filled.Settings.name
                )

                }
            }
        }
    )
}