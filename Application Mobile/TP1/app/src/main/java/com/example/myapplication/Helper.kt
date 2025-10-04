package com.example.myapplication

class Helper {
    companion object{
        fun ChoisirVehiculeSelonId( idRecherche : Int) : Vehicules{
            var vehiculeChoisis : Vehicules = Vehicules(0,"",0,0,0,0)
            for(vehicule in listeVehicules){
                if(vehicule.id == idRecherche){
                    vehiculeChoisis = vehicule
                }
            }
            return vehiculeChoisis
        }

    }
}