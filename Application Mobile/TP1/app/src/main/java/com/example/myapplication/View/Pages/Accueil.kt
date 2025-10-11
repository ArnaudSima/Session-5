package com.example.myapplication.View.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.View.Routes

@Composable
fun Accueil(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Bienvenue", fontSize = 40.sp)
        Button(
            onClick = { navController.navigate(Routes.Inscription.route) },
            content = { Text("S'inscrire") })
        Button(
            onClick = { navController.navigate(Routes.Connexion.route) },
            content = { Text("Se connecter") })
    }
}