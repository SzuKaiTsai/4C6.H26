package ca.qc.cstj.inkify.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.models.InkifySettings
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class SettingRepository(private val dataStore: DataStore<Preferences>){

    object PreferenceKeys{
        val NAME = stringPreferencesKey("name")
        val DEFAULT_NOTE_COLOR = stringPreferencesKey("default_note_color")

        // VERSION 2
        val JSON_SETTINGS = stringPreferencesKey("json_settings")
    }

    val preferences = dataStore.data.map {
        val name = it[PreferenceKeys.NAME] ?: ""
        val defaultNoteColor = it[PreferenceKeys.DEFAULT_NOTE_COLOR] ?: Constants.NOTES_COLORS.random()
        InkifySettings(name,defaultNoteColor) // ceci est retourné par la propriété
    }

    // Version 2
    val jsonPreferences = dataStore.data.map {
        val jsonString = it[PreferenceKeys.JSON_SETTINGS] ?: Json.encodeToString(InkifySettings())
        // Decode == DéSerializer
        Json.decodeFromString<InkifySettings>(jsonString)
    }

    suspend fun save(settings: InkifySettings){
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.NAME] = settings.name
            preferences[PreferenceKeys.DEFAULT_NOTE_COLOR] = settings.noteDefaultColor

            // Version 2
            preferences[PreferenceKeys.JSON_SETTINGS] = Json.encodeToString(settings)
        }
    }

    suspend fun reset(){
        save(InkifySettings())
    }

}