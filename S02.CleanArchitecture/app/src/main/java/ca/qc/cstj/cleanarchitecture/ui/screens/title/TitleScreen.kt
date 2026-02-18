package ca.qc.cstj.cleanarchitecture.ui.screens.title

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.cleanarchitecture.R
import ca.qc.cstj.cleanarchitecture.core.ObserveAsEvents
import ca.qc.cstj.cleanarchitecture.core.stringResourceWithContext

@Composable
fun TitleScreen(
    viewModel: TitleViewModel = viewModel(),
    toMeditationScreen:(String)-> Unit
){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) {
        when(it){
            TitleUiEvent.OnLoginError -> Toast.makeText(context, context.stringResourceWithContext(R.string.login_error),Toast.LENGTH_LONG).show()
            TitleUiEvent.OnLoginSuccess -> toMeditationScreen(uiState.name)
        }
    }

    TitleContent(
        uiState = uiState,
        onAction = {viewModel.onAction(it)}
    )
}

@Composable
fun TitleContent(
    uiState: TitleUiState,
    onAction: (TitleAction)->Unit = {}

){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Image(
            painter = painterResource(R.drawable.kiwiflow),
            contentDescription = stringResource(R.string.kiwiflow)
        )

        Text(text= stringResource(R.string.kiwiflow), style = MaterialTheme.typography.headlineLarge)

        OutlinedTextField(
            value = uiState.name,
            onValueChange = {onAction(TitleAction.OnUpdateName(it))},
            label = {
                Text(text= stringResource(R.string.name))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            isError = uiState.isError
        )
        OutlinedTextField(
            value = uiState.password,
            onValueChange = {onAction(TitleAction.OnUpdatePassword(it))},
            label = {
                Text(text= stringResource(R.string.password))
            },
            trailingIcon = {
                IconButton(onClick = {
                    onAction(TitleAction.OnTogglePasswordVisibility)
                }
                ) {
                    val icon = if(uiState.isPasswordVisible)
                        Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    Icon(imageVector = icon,
                        stringResource(R.string.toggle_password_visibility)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            visualTransformation = if(uiState.isPasswordVisible)
                 VisualTransformation.None else PasswordVisualTransformation(),
            isError = uiState.isError
        )

        Button(
            onClick = {
                onAction(TitleAction.OnLoginClicked)
            }
        ) {
            Text(text = stringResource(R.string.login), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TitleContentPreview(){
    TitleContent(uiState = TitleUiState("Test","password"))
}