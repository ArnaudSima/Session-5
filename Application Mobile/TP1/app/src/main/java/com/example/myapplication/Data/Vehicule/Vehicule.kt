package com.example.myapplication.Data.Vehicule
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.myapplication.Data.utilisateur.Utilisateur

@Entity(
    tableName = "vehicule",
    foreignKeys = [ForeignKey(
        entity = Utilisateur::class,
        parentColumns = arrayOf("idUtilisateur"),
        childColumns = arrayOf("idUtilisateur"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Vehicule(
    @PrimaryKey(autoGenerate = true)
    val idVehicule: Int = 0,
    var titre: String,
    var kilometrage: Int,
    var image: Int,
    var prix: Int,
    @ColumnInfo(index = true)
    val idUtilisateur: Int
)