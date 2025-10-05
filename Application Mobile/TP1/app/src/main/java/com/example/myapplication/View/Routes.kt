package com.example.myapplication.View

sealed class Routes(val route : String){
    object Connexion : Routes("connexion")
    object DashBoard : Routes("dash_board")
    object Modification : Routes("modification")
}