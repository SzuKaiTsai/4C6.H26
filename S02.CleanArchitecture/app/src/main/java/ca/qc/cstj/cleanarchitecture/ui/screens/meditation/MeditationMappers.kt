package ca.qc.cstj.cleanarchitecture.ui.screens.meditation

import androidx.compose.ui.graphics.Color
import ca.qc.cstj.cleanarchitecture.R
import ca.qc.cstj.cleanarchitecture.models.MeditationCategory
import ca.qc.cstj.cleanarchitecture.models.MeditationSession
import ca.qc.cstj.cleanarchitecture.ui.theme.Blue1
import ca.qc.cstj.cleanarchitecture.ui.theme.Blue2
import ca.qc.cstj.cleanarchitecture.ui.theme.Blue3
import ca.qc.cstj.cleanarchitecture.ui.theme.Green1
import ca.qc.cstj.cleanarchitecture.ui.theme.Green2
import ca.qc.cstj.cleanarchitecture.ui.theme.Green3
import ca.qc.cstj.cleanarchitecture.ui.theme.Pink1
import ca.qc.cstj.cleanarchitecture.ui.theme.Pink2
import ca.qc.cstj.cleanarchitecture.ui.theme.Pink3

val MeditationSession.icon : Int get() = when(this.category){
    MeditationCategory.SLEEP -> R.drawable.sleep
    MeditationCategory.RELAX -> R.drawable.relax
    MeditationCategory.FOCUS -> R.drawable.focus
}

val MeditationSession.colors: Triple<Color, Color, Color> get() = when(this.category){
    MeditationCategory.SLEEP -> Triple(Blue1, Blue2, Blue3)
    MeditationCategory.RELAX -> Triple(Pink1, Pink2, Pink3)
    MeditationCategory.FOCUS -> Triple(Green1, Green2, Green3)
}

val MeditationSession.backgroundColor: Color get() = this.colors.first