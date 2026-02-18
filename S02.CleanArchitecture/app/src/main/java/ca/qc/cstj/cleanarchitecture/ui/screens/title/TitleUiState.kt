package ca.qc.cstj.cleanarchitecture.ui.screens.title

data class TitleUiState(
    val name: String="",
    val password: String="",
    val isPasswordVisible: Boolean = false,
    val isError: Boolean = false
)
