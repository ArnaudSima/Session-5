package com.example.myapplication.View

sealed class Routes(val route : String){
    object Accueil : Routes("accueil")
    object Connexion : Routes("connexion")
    object Inscription : Routes("inscription")
    object DashBoard : Routes("dash_board")
    object Modification : Routes("modification")
}