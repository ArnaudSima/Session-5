package com.example.myapplication.Middleware.Vehicules
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "Vehicules")
    data class Vehicules(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        var titre: String,
        var kilometrage: Int,
        var image: Int,
        var prix: Int
    )
