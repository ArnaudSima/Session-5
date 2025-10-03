package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val fontFamily = FontFamily(Font(R.font.yesteryear, FontWeight.Normal))

class Routeur : ComponentActivity() {
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
            }) { padding ->
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
                startDestination = Ecrans.Connexion.route
            ) {
                composable(route = Ecrans.Connexion.route) {
                    Connexion(navController)
                }
                composable(route = Ecrans.DashBoard.route) { DashBoard(navController) }
            }
        }

    }
    @Composable
    fun StateTopBar(){

    }
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {

    }
}