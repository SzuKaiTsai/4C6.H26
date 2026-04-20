package ca.qc.cstj.funmania.ui.screens.orientation

import android.content.Intent
import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import ca.qc.cstj.funmania.R

@Composable
fun OrientationScreen() {
    //TODO:
}

@Composable
private fun LandscapeMode() {
    Row(
        modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp).verticalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.fillMaxSize(0.5f)) {
            //TODO: Youtube
            SoundSection()
        }
        IntentsSection()
    }
}


@Composable
private fun PortraitMode() {
    Column(
        modifier = Modifier.fillMaxSize().padding(4.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        //TODO: Youtube
        SoundSection()
        IntentsSection()
    }
}

@Composable
fun SoundSection() {
    //https://pixabay.com/sound-effects/
    val mediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.chonologyoflove)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            IconButton(onClick = { mediaPlayer.start() }) {
                Icon(
                    imageVector = Icons.Default.PlayCircle,
                    contentDescription = "",
                    Modifier.size(100.dp)
                )
            }

            IconButton(onClick = { mediaPlayer.pause() }) {
                Icon(
                    imageVector = Icons.Default.PauseCircle,
                    contentDescription = "",
                    Modifier.size(100.dp)
                )
            }
        }
    }
}

@Composable
fun IntentsSection() {
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            Button(onClick = {
                //TODO:
            }) {
                Text(text = stringResource(R.string.phone))
            }
        }

        item {
            Button(onClick = {
                //TODO:
            }) {
                Text(text = stringResource(R.string.sms))
            }
        }

        item {
            Button(onClick = {
                //TODO:
            }) {
                Text(text = stringResource(R.string.maps))
            }
        }

        item {
            Button(onClick = {
                //TODO:
            }) {
                Text(text = stringResource(R.string.calendar))
            }
        }
    }
}