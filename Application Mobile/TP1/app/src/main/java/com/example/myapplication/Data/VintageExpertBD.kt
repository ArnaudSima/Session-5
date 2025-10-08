package com.example.myapplication.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.Middleware.Vehicules.Vehicules
import com.example.myapplication.Middleware.Vehicules.VehiculesDao

@Database(
    entities = [Vehicules::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun VehiculesDao(): VehiculesDao
}