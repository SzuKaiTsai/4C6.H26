package ca.qc.cstj.inkify.ui.screens.settings

sealed interface SettingsAction {
    data class OnSaveName(val name: String): SettingsAction
    data class OnColorChange(val hexColor:String): SettingsAction
    data object OnResetSettings: SettingsAction
}

sealed interface SettingsEvent {
    data object Saved: SettingsEvent
    data object Error: SettingsEvent
}