package ca.qc.cstj.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import ca.qc.cstj.cleanarchitecture.ui.navigation.Route
import ca.qc.cstj.cleanarchitecture.ui.screens.meditation.MeditationScreen
import ca.qc.cstj.cleanarchitecture.ui.screens.title.TitleScreen
import ca.qc.cstj.cleanarchitecture.ui.theme.CleanArchitectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitectureTheme(
                dynamicColor = false
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                )
                {   innerPaddings ->
                    val backstack = remember { mutableStateListOf<Route>(Route.TitleRoute) }
                    NavDisplay(
                        modifier = Modifier.fillMaxSize().padding(innerPaddings),
                        backStack = backstack,
                        entryProvider = { key ->
                            when(key){
                            is Route.MeditationRoute -> NavEntry(key){
                                MeditationScreen(key)
                            }
                            Route.TitleRoute -> NavEntry(key){
                                TitleScreen(
                                    toMeditationScreen = {
                                        backstack.add(Route.MeditationRoute(it))
                                    }
                                )
                            }
                        }

                        }
                    )

                }

                }
            }
        }
    }