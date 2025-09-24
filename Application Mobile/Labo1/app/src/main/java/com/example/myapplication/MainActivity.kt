package com.example.myapplication

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.math.exp


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Greeting()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ), title = { Text("Labo 1 ") }, actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.Delete, contentDescription = "Delete"
                )
            }
            IconButton(onClick = {}) { Icon(Icons.Default.Add, contentDescription = "Add") }
        })
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PokemonGamePlay()
        }
    }
}

@Composable
fun PokemonGamePlay() {
    val CaterPie = Pokemon(
        pointsDeVies = 20, idDrawable = R.drawable.caterpie, force = 10, description = "caterpie"
    )
    val Magikarp = Pokemon(
        pointsDeVies = 50, idDrawable = R.drawable.magikarp, force = 25, description = "magikarp"
    )
    val Gyarados = Pokemon(
        pointsDeVies = 100, idDrawable = R.drawable.gyarados, force = 50, description = "gyarados"
    )
    val Pikachu = Pokemon(
        pointsDeVies = 50, idDrawable = R.drawable.pikachu, force = 25, description = "pikachu"
    )
    val list = listOf(CaterPie, Magikarp, Gyarados, Pikachu)
    var indicePokemonActuel by remember { mutableIntStateOf(0) }
    var pokemonActuelPointsDeVies by remember { mutableIntStateOf(list[indicePokemonActuel].pointsDeVies) }
    var pokemonActuelDescription by remember { mutableStateOf(list[indicePokemonActuel].description) }
    var pokemonActuelIdImage by remember { mutableIntStateOf(list[indicePokemonActuel].idDrawable) }
    var pokemonActuelForce by remember { mutableIntStateOf(list[indicePokemonActuel].force) }
    var JoueurPointsDeVies by remember { mutableIntStateOf(100) }
    var JoueurForce by remember { mutableIntStateOf(10) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 100.dp),
        verticalArrangement = Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text("Joueur")
        Text("$JoueurPointsDeVies points de vie")
        Text("$JoueurForce force")

    }
    Image(
        painter = painterResource(id = pokemonActuelIdImage),
        contentDescription = pokemonActuelDescription
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 100.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text("$pokemonActuelPointsDeVies points de vie")
        Button(onClick = {
            pokemonActuelPointsDeVies -= JoueurForce
            if (pokemonActuelPointsDeVies <= 0) {
                indicePokemonActuel++
                pokemonActuelForce = list[indicePokemonActuel].force
                pokemonActuelDescription = list[indicePokemonActuel].description
                pokemonActuelIdImage = list[indicePokemonActuel].idDrawable
                pokemonActuelPointsDeVies = list[indicePokemonActuel].pointsDeVies
            } else {
                JoueurPointsDeVies -= pokemonActuelForce
            }
            if(JoueurPointsDeVies <= 0){
                indicePokemonActuel = 0
                pokemonActuelForce = list[indicePokemonActuel].force
                pokemonActuelDescription = list[indicePokemonActuel].description
                pokemonActuelIdImage = list[indicePokemonActuel].idDrawable
                pokemonActuelPointsDeVies = list[indicePokemonActuel].pointsDeVies
                JoueurPointsDeVies = 100
            }
        }, content = { Text("Attaquer") })
    }
}

class Pokemon constructor(
    val pointsDeVies: Int, val idDrawable: Int, val force: Int, val description: String
)

@Preview(showBackground = true)

@Composable
fun GreetingPreview() {

    MyApplicationTheme {
        Greeting()
    }
}