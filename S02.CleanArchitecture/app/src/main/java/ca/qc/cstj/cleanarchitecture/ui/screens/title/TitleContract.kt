package ca.qc.cstj.cleanarchitecture.ui.screens.title

sealed interface TitleAction{
    data class OnUpdateName(val name: String): TitleAction
    data class OnUpdatePassword(val password: String): TitleAction
    data object OnTogglePasswordVisibility : TitleAction
    data object OnLoginClicked : TitleAction
}

sealed interface TitleUiEvent{
    data object OnLoginSuccess : TitleUiEvent
    data object OnLoginError : TitleUiEvent
}