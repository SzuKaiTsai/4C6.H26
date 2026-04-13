package ca.qc.cstj.bottomnavigation.ui.screens.contents

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class DetailOneViewModel(
    val href:String,
    private val application: Application
) : AndroidViewModel(application)