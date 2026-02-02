package ca.qc.cstj.premiereapllication.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.premiereapllication.R
import io.github.vinceglb.confettikit.compose.ConfettiKit
import io.github.vinceglb.confettikit.core.Party
import io.github.vinceglb.confettikit.core.emitter.Emitter
import kotlin.time.Duration.Companion.seconds

@Composable
fun GameScreen(viewModel: GameViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if(uiState.state == GameState.WIN) {
        ConfettiKit(
            modifier = Modifier.fillMaxSize(),
            parties = listOf(
                Party(emitter = Emitter(duration = 5.seconds).perSecond(30))
            )
        )
    }
    //var numberToTry by remember { mutableIntStateOf(50) }
    //val numberToGuess by remember { mutableIntStateOf((0..100).random())}
    //var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = uiState.number.toString(), fontSize = 18.sp)
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { viewModel.increment()}) {
                    Text(text = "+", fontSize = 20.sp)
                }
                Button(onClick = { viewModel.decrement()}) {
                    Text(text = "-", fontSize = 20.sp)
                }
            }

        }
        if(uiState.state == GameState.WIN){
            Button(modifier = Modifier
                .fillMaxWidth(0.5f),
                onClick = { viewModel.newGame() }
            ) {
                Text(text = stringResource(R.string.new_game))
            }
        } else{
        Button(modifier = Modifier
            .fillMaxWidth(0.5f),
            onClick = { viewModel.validate() }
        ) {
            Text(text = stringResource(R.string.try_msg))
        }
        }
        Text(text = when(uiState.state) {
            GameState.NEW_GAME -> ""
            GameState.TOO_HIGH -> stringResource(R.string.too_high)
            GameState.TOO_LOW -> stringResource(R.string.too_low)
            GameState.WIN -> stringResource(R.string.you_win)
        },
            fontSize = 24.sp)
    }
}