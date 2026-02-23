package ca.qc.cstj.inkify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import ca.qc.cstj.inkify.ui.navigation.Route
import ca.qc.cstj.inkify.ui.screens.notes.add.AddNoteScreen
import ca.qc.cstj.inkify.ui.theme.InkifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            InkifyTheme(dynamicColor = false) {

                val backStack = remember { mutableStateListOf<Route>(Route.AddNoteRoute) }

                NavDisplay(
                    modifier = Modifier.fillMaxSize(),
                    backStack = backStack,
                    popTransitionSpec = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Down,
                            animationSpec = tween(500)
                        ) + fadeIn() togetherWith slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Down,
                            animationSpec = tween(500)
                        ) + fadeOut()
                    },
                    transitionSpec = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(500)
                        ) + fadeIn() togetherWith slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(500)
                        ) + fadeOut()
                    },
                    predictivePopTransitionSpec = {
                        scaleIn(initialScale = 0.9f) + fadeIn() togetherWith scaleOut(targetScale = 1.1f) + fadeOut()
                    },
                    entryProvider = { route ->
                        when(route) {
                            Route.AddNoteRoute -> NavEntry(route) {
                                AddNoteScreen(
                                    onNavigateBack = { backStack.removeLastOrNull() },
                                    toSettingsScreen = {}
                                )
                            }
                        }
                    }
                )
            }

        }
    }
}


