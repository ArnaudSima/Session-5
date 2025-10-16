package com.example.myapplication.View

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.MiddleWare.UtilisateurViewModel
import com.example.myapplication.MiddleWare.VehiculeViewModel
import com.example.myapplication.R
import com.example.myapplication.View.Enums.BottomBar
import com.example.myapplication.View.Routes

val fontFamily = FontFamily(
    Font(
        R.font.yesteryear,
        FontWeight.Normal
    )
)
private var idUtilisateur = 0
private var idVehiculeAffiche = 0;
var routeActuelle: String by mutableStateOf(Routes.Accueil.route)
var navigationManuelle: Boolean by mutableStateOf(false)
private const val tag: String = "Routeur"

class Routeur : ComponentActivity() {

    private val vehiculeViewModel: VehiculeViewModel by viewModels()
    private val utilisateurViewModel: UtilisateurViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                topBar = {
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


                },
                bottomBar = {
                    if (routeActuelle == Routes.DashBoard.route) {
                        BottomBarDashBoard()
                    }

                }
            ) { padding ->

                Navigation(modifier = Modifier.padding(padding))

            }
        }
    }


    @Composable
    fun Navigation(modifier: Modifier) {

        val navController = rememberNavController()
        val historiqueDeNavigation = navController.currentBackStackEntryFlow
        LaunchedEffect(Unit) {
            historiqueDeNavigation.collect { entreeHistoriqueNavigation ->
                val route = entreeHistoriqueNavigation.destination.route

                if (route == null) {
                    return@collect
                }
                routeActuelle = route
                Log.d(tag, "Nouvelle route détectée : $route")

            }
        }
        LaunchedEffect(navigationManuelle) {
            if (navigationManuelle) {
                Log.d(tag, "Migration vers  : $routeActuelle")
                navController.navigate(routeActuelle)
                navigationManuelle = false
            }

        }
        Box(modifier = modifier) {
            NavHost(
                navController = navController,
                startDestination = Routes.Accueil.route
            ) {
                composable(route = Routes.Accueil.route) {
                    _root_ide_package_.com.example.myapplication.View.Pages.Accueil(navController)
                }
                composable(route = Routes.Connexion.route) {
                    _root_ide_package_.com.example.myapplication.View.Pages.Connexion(
                        utilisateurViewModel,
                        onChangeIdUtilisateur = { idUtilisateur = it },
                    )
                }
                composable(route = Routes.DashBoard.route) {
                    _root_ide_package_.com.example.myapplication.View.Pages.DashBoard(
                        navController,
                        onChangeIdVehicule = { idVehiculeAffiche = it },
                        vehiculeViewModel,
                        idUtilisateur
                    )
                }
                composable(route = Routes.Modification.route) {
                    _root_ide_package_.com.example.myapplication.View.Pages.AffichageVehiculeSpecifique(
                        navController,
                        vehiculeViewModel,
                        idVehiculeAffiche,
                        idUtilisateur
                    )
                }
                composable(route = Routes.Inscription.route) {
                    _root_ide_package_.com.example.myapplication.View.Pages.Inscription(
                        navController,
                        utilisateurViewModel,
                        onChangeIdUtilisateur = { idUtilisateur = it })
                }
                composable(route = Routes.Ajout.route) {
                    _root_ide_package_.com.example.myapplication.View.Pages.AjoutVehicule(
                        navController,
                        vehiculeViewModel,
                        idUtilisateur
                    )
                }
            }
        }

    }

    @Composable
    fun BottomBarDashBoard(

    ) {
        return BottomAppBar(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {
                    navigationManuelle = true
                    routeActuelle = Routes.Ajout.route

                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "ajouterVehicule",
                        modifier = Modifier.border(
                            width = 3.dp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            shape = CircleShape
                        )
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {

    }
}