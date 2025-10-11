package com.example.myapplication.Data.utilisateur

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UtilisateurDao {
    @Insert
    fun insert(utilisateur : Utilisateur)
    @Update
    fun update(utilisateur: Utilisateur)
    @Delete
    fun delete(utilisateur: Utilisateur)
    @Query("SELECT * FROM utilisateur WHERE motDePasse = :motDePasse AND nomUtilisateur = :nomUtilisateur")
    fun verifierNomUtilisateurEtMotDePasse(motDePasse : Int,nomUtilisateur: String) : Utilisateur?
    @Query("SELECT * FROM utilisateur")
    fun utilisateurs():Flow<List<Utilisateur>>

}