package ca.qc.cstj.inkify.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.qc.cstj.inkify.data.local.Converters
import ca.qc.cstj.inkify.data.local.dao.NoteDao
import ca.qc.cstj.inkify.data.local.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    //DAO
    abstract fun noteDao(): NoteDao

    // companion object est la partie statique d'une classe
    companion object {
        // @Volatile: Assure que la variable est immédiatement visible par tous les threads
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Si l'instance existe, on la retourne
            return INSTANCE ?: synchronized(this) {
                // Sinon, on crée la base de données
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "inkify_database" // Le nom du fichier .db
                )
                // Optionnel : Pour détruire la BDD si on change la version sans migration (utile en dev)
                //.fallbackToDestructiveMigration()
                .build()

                INSTANCE = instance
                instance
            }
        }
    }

}