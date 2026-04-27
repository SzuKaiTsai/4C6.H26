package ca.qc.cstj.funmania.ui.screens.orientation

import android.content.Intent
import android.content.res.Configuration
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.LocalLifecycleOwner
import ca.qc.cstj.funmania.R
import ca.qc.cstj.funmania.core.ui.YoutubePlayer
import com.google.android.gms.cast.CastRemoteDisplay

@Composable
fun OrientationScreen() {
    val orientation = LocalConfiguration.current.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        PortraitMode()
    } else {
        LandscapeMode()
    }
}

@Composable
private fun LandscapeMode() {
    Row(
        modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp).verticalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.fillMaxSize(0.5f)) {
            YoutubePlayer(youtubeVideoId = "5anLPw0Efmo", lifecycleOwner = LocalLifecycleOwner.current)
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
        YoutubePlayer(youtubeVideoId = "5anLPw0Efmo", lifecycleOwner = LocalLifecycleOwner.current)
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
                val dialIntent = Intent(Intent.ACTION_DIAL, "tel:450-436-1580".toUri())
                context.startActivity(dialIntent)
            }) {
                Text(text = stringResource(R.string.phone))
            }
        }

        item {
            Button(onClick = {
                val smsIntent = Intent(Intent.ACTION_VIEW, "smsto:450-436-1580".toUri())
                smsIntent.putExtra("sms_body", "BonJour d'Android")
                context.startActivity(smsIntent)
            }) {
                Text(text = stringResource(R.string.sms))
            }
        }

        item {
            Button(onClick = {
                val googleMapsIntent = Intent(Intent.ACTION_VIEW, "geo:0,0?q=restaurants".toUri())
                googleMapsIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(googleMapsIntent)
            }) {
                Text(text = stringResource(R.string.maps))
            }
        }

        item {
            Button(onClick = {
                val calendarIntent = Intent(Intent.ACTION_MAIN)
                calendarIntent.addCategory(Intent.CATEGORY_APP_CALENDAR)
                context.startActivity(calendarIntent)
            }) {
                Text(text = stringResource(R.string.calendar))
            }
        }
    }
}