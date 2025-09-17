package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Box() {
                    ScaffoldExemple()                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExemple() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mon Application")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    IconButton(onClick = { /*Faire quelque chose*/ }) {
                        Icon(Icons.Default.Home, contentDescription = "Accueil")
                    }
                    IconButton(onClick = { /*Faire quelque chose*/ }) {
                        Icon(Icons.Default.Search, contentDescription = "Recherche")
                    }
                    IconButton(onClick = { /*Faire quelque chose*/ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Paramètres")
                    }
                }
            }
        }


    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            JeuDe()
        }
    }
}

@Composable
fun JeuDe() {    var scoreJoueur1 by remember { mutableIntStateOf(0) }
    var scoreJoueur2 by remember { mutableIntStateOf(0) }
    var indiceListe by remember { mutableIntStateOf(0) }
    var indiceDuJoueurEnJeu by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    val listeDes = arrayOf(
        painterResource(id = R.drawable.dice_1),
        painterResource(id = R.drawable.dice_2),
        painterResource(id = R.drawable.dice_3),
        painterResource(id = R.drawable.dice_4),
        painterResource(id = R.drawable.dice_5),
        painterResource(id = R.drawable.dice_6)
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = listeDes[indiceListe], contentDescription = "de")
        Row {
            Button(
                modifier = Modifier.absolutePadding(0.dp, 0.dp, 5.dp, 0.dp),
                onClick = {
                    indiceListe = Random.nextInt(0, 5)
                    var scoreJoueur: Int
                    if (indiceDuJoueurEnJeu == 0) {
                        scoreJoueur1 += CalculerScoreJoueur(indiceListe, scoreJoueur1)
                        scoreJoueur = scoreJoueur1
                        indiceDuJoueurEnJeu++
                    } else {

                        scoreJoueur2 += CalculerScoreJoueur(indiceListe, scoreJoueur2)
                        scoreJoueur = scoreJoueur2
                        indiceDuJoueurEnJeu--
                    }

                    if (scoreJoueur >= 25) {
                        if (indiceDuJoueurEnJeu == 0) {
                            Toast.makeText(context, "🎉 Joueur 2 gagne !", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "🎉 Joueur 2 gagne !", Toast.LENGTH_LONG).show()

                        }
                        scoreJoueur1 = 0
                        scoreJoueur2 = 0
                    }
                }) { Text("Rouler") }
            Button(onClick = {
                if (indiceDuJoueurEnJeu == 0) {
                    indiceDuJoueurEnJeu++
                } else {
                    indiceDuJoueurEnJeu--
                }
            }) { Text("Garder") }
        }
        Row {
            Text(
                modifier = Modifier.absolutePadding(0.dp, 0.dp, 5.dp, 0.dp),
                text = "Joueur 1 : $scoreJoueur1"
            )
            Text("Joueur 2 : $scoreJoueur2")
        }

    }

}


fun CalculerScoreJoueur(indiceListe: Int, scoreJoueur: Int): Int {
    var scoreAjoutable = 0;
    if (indiceListe != 0) {
        scoreAjoutable = indiceListe + 1
    } else {
        scoreAjoutable = -scoreJoueur
    }
    return scoreAjoutable
}






