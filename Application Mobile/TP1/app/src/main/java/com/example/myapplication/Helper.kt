package com.example.myapplication

import android.util.Log

class Helper {
    companion object {
        val Tag = "Helper"
        fun ChoisirVehiculeSelonId(idRecherche: Int): Vehicule {
            var vehiculeChoisis: Vehicule = Vehicule(0, "", 0, 0, 0)
            for (vehicule in listeVehicules) {
                if (vehicule.id == idRecherche) {
                    vehiculeChoisis = vehicule
                }
            }
            return vehiculeChoisis
        }

        fun ModifierVehiculesSelonId(idRecherche: Int, vehiculeModifie: Vehicule) {
            val index = listeVehicules.indexOfFirst { it.id == idRecherche }
            try {
                if (index == -1) {
                    Log.d(Tag, "Le vehicule n'existe pas dans la liste")
                    return;
                }
                Log.d(Tag, "Id du vehicule a modifier : $idRecherche")
                listeVehicules[index] = vehiculeModifie
                Log.d(Tag, "Vehicule modifie avec succes!")
            } catch (e: Exception) {
                Log.d(Tag, "Erreur lors de la modification du vehicule : $e")
            }


        }

    }
}