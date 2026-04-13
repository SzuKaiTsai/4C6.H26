package ca.qc.cstj.bottomnavigation.ui.screens.contents

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.bottomnavigation.R

@Composable
fun HomeScreen() {
    Text(text = stringResource(R.string.home))
}

@Composable
fun OneScreen(
    onDetailClick : () -> Unit
) {
    Column {
        Text(text = stringResource(R.string.one))
        Button(
            onClick = onDetailClick
        ) {
            Text(text = stringResource(R.string.details))
        }
    }
}

@Composable
fun DetailOneScreen(
    href: String,
    application: Application = LocalContext.current.applicationContext as Application,
    viewModel: DetailOneViewModel = viewModel() {
        DetailOneViewModel(
            href = href,
            application = application
        )
    }
) {
    Text(text = href)
}

@Composable
fun TwoScreen() {
    Text(text = stringResource(R.string.two))
}

@Composable
fun ThreeScreen() {
    Text(text = stringResource(R.string.three))
}