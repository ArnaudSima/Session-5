package com.example.myapplication.Data.Vehicule

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
public interface VehiculesDao{
    @Insert
    fun insert(vehicule : Vehicule)
    @Update
    fun update(vehicule: Vehicule)
    @Delete
    fun delete(vehicule: Vehicule)
    @Query("SELECT v.* FROM vehicule as v INNER JOIN utilisateur as u ON v.idUtilisateur = u.idUtilisateur WHERE v.idUtilisateur = :idSelectionne")
    fun getVehiculeDeUtilisateur(idSelectionne : Int) : Flow<List<Vehicule>>
    @Query("SELECT * FROM vehicule WHERE idVehicule = :idSelectionne")
    fun getVehiculeParId(idSelectionne : Int) : Flow<Vehicule>
}