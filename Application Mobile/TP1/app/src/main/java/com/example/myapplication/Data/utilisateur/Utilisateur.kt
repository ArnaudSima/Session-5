package com.example.myapplication.Data.utilisateur

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "utilisateur")
data class Utilisateur(
    @PrimaryKey(autoGenerate = true)
    val idUtilisateur: Int = 0,
    val nomUtilisateur: String,
    var motDePasse: Int
)