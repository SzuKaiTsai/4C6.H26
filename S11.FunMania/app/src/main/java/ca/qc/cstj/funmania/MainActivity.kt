package ca.qc.cstj.funmania

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ca.qc.cstj.funmania.ui.navigation.Route
import ca.qc.cstj.funmania.ui.screens.main.MainScreen
import ca.qc.cstj.funmania.ui.screens.map.MapScreen
import ca.qc.cstj.funmania.ui.theme.FunManiaTheme
import com.google.android.gms.maps.model.LatLng

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunManiaTheme {

                val backStack = remember { mutableStateListOf<Route>(Route.ToMainScreen) }

                NavDisplay(
                    modifier = Modifier.fillMaxSize(),
                    backStack = backStack,
                    transitionSpec = { slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut() },
                    popTransitionSpec = { slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut() },
                    predictivePopTransitionSpec = { slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut() },
                    entryProvider = entryProvider {
                        entry<Route.ToMainScreen> {
                            MainScreen(
                                toMapScreen = { backStack.add(Route.ToMap(it)) }
                            )
                        }
                        entry<Route.ToMap> {
                            MapScreen(it.latLng)
                        }
                    })
            }
        }
    }
}
