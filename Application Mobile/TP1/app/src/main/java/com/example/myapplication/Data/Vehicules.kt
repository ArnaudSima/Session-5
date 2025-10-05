package com.example.myapplication.Data
import com.example.myapplication.Middleware.Vehicule
import com.example.myapplication.R
class Vehicules {
    var ListeVehicules = mutableListOf<Vehicule>()

    constructor() {
        ListeVehicules = mutableListOf<Vehicule>(
            Vehicule(
                id = 1,
                titre = "1977 Ford Mustang Shelby",
                kilometrage = 120000,
                image = R.drawable.shelby,
                prix = 12000
            ),
            Vehicule(
                id = 2,
                titre = "1980 Ford Bronco",
                kilometrage = 80000,
                image = R.drawable.bronco,
                prix = 15000,

                ),
            Vehicule(
                id = 3,
                titre = "1982 chevrolet silverado",
                kilometrage = 120000,
                image = R.drawable.silverado,
                prix = 10000
            ),
            Vehicule(
                id = 4,
                titre = "1993 shadow vt1100",
                kilometrage = 70000,
                image = R.drawable.shadow,
                prix = 2000
            ),
        )
    }
}