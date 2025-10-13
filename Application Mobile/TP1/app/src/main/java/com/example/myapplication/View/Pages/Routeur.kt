package com.example.myapplication.View.Pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.MiddleWare.UtilisateurViewModel
import com.example.myapplication.MiddleWare.VehiculeViewModel
import com.example.myapplication.View.Enums.BottomBar
import com.example.myapplication.View.Routes

val fontFamily = FontFamily(
    Font(
        com.example.myapplication.R.font.yesteryear,
        FontWeight.Normal
    )
)
private var idUtilisateur = 0
private var idVehiculeAffiche = 0;
private var bottomBarAffiche by mutableStateOf(BottomBar.DEFAULT)

class Routeur : ComponentActivity() {

    private val vehiculeViewModel: VehiculeViewModel by viewModels()
    private val utilisateurViewModel: UtilisateurViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Vintage Expert",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 45.sp
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        titleContentColor = MaterialTheme.colorScheme.onSecondary,
                        actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                    ),


                    )
            }
            ) { padding ->
                Navigation(modifier = Modifier.padding(padding))

            }
        }
    }


    @Composable
    fun Navigation(modifier: Modifier) {

        val navController = rememberNavController()

        Box(modifier = modifier) {
            NavHost(
                navController = navController,
                startDestination = Routes.Accueil.route
            ) {
                composable(route = Routes.Accueil.route) {
                    Accueil(navController)
                }
                composable(route = Routes.Connexion.route) {
                    Connexion(
                        navController,
                        utilisateurViewModel,
                        onChangeIdUtilisateur = { idUtilisateur = it },
                        onChangeBottomBar = { bottomBarAffiche = it }
                    )
                }
                composable(route = Routes.DashBoard.route) {
                    DashBoard(
                        navController,
                        onChangeIdVehicule = { idVehiculeAffiche = it },
                        vehiculeViewModel,
                        idUtilisateur
                    )
                }
                composable(route = Routes.Modification.route) {
                    AffichageVehiculeSpecifique(
                        navController,
                        vehiculeViewModel,
                        idVehiculeAffiche,
                        idUtilisateur
                    )
                }
                composable(route = Routes.Inscription.route) {
                    Inscription(
                        navController,
                        utilisateurViewModel,
                        onChangeIdUtilisateur = { idUtilisateur = it })
                }
                composable(route = Routes.Ajout.route) {
                    AjoutVehicule(navController, vehiculeViewModel, idUtilisateur)
                }
            }
        }

    }

    @Composable
    fun StateTopBar() {

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {

    }
}