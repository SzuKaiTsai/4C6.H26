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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.qc.cstj.cleanarchitecture.ui.screens.meditation.MeditationScreen
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
                    Column(modifier = Modifier
                        .padding(innerPaddings)
                        .padding(horizontal = 4.dp)
                    ) {
                        MeditationScreen()
                    }

                }

                }
            }
        }
    }