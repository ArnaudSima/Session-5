package com.example.myapplication

sealed class Ecrans(val route : String){
    object Connexion : Ecrans("connexion")
    object DashBoard : Ecrans("dash_board")
    object Modification : Ecrans("modification")
}