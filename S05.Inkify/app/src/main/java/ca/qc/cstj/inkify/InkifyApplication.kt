package ca.qc.cstj.inkify

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import ca.qc.cstj.inkify.data.AppDatabase

class InkifyApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val dataStore: DataStore<Preferences> by preferencesDataStore("inkify_datastore")
}