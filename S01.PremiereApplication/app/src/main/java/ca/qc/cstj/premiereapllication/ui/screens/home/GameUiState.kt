package ca.qc.cstj.premiereapllication.ui.screens.home

import ca.qc.cstj.premiereapllication.Constants

enum class GameState{
    NEW_GAME,
    TOO_HIGH,
    TOO_LOW,
    WIN
}

data class GameUiState(
    val number: Int = Constants.DEFAULT_NUMBER,
    val state: GameState = GameState.NEW_GAME
)

//private val = pas de get / pas de set
// val = seulement un get(read only)
// var = get / set