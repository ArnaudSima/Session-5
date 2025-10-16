package com.example.myapplication.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.Data.Vehicule.VehiculesDao
import com.example.myapplication.Data.utilisateur.Utilisateur
import com.example.myapplication.Data.utilisateur.UtilisateurDao


@Database(
    entities = [Vehicule::class, Utilisateur::class],
    version = 2,
    exportSchema = false
)
abstract class VintageExpertBD : RoomDatabase() {
    abstract fun VehiculesDao(): VehiculesDao
    abstract fun UtilisateurDao(): UtilisateurDao

    companion object {
        @Volatile
        private var Instance: VintageExpertBD? = null;
        fun getDatabase(context: Context): VintageExpertBD? {
            Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    VintageExpertBD::class.java,
                    "vintage_expert_bd"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration(true)
                    .build().also { Instance = it }
            }
            return Instance

        }
    }
}