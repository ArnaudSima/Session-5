package com.example.myapplication

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }

    Scaffold(

        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Labo 1")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    Row {
                        IconButton(
                            onClick = {
                                expanded1 = true
                            },
                        ) { Icon(Icons.Default.Delete, contentDescription = "delete") }

                        DropdownMenu(
                            expanded = expanded1,
                            onDismissRequest = { expanded1 = false }) {
                            DropdownMenuItem(
                                text = { Text("Add") },
                                onClick = {}
                            )
                        }

                        IconButton(
                            onClick = { expanded2 = true },

                            ) {
                            Icon(Icons.Default.Add, contentDescription = "Ajouter")
                        }
                        DropdownMenu(
                            expanded = expanded2,
                            onDismissRequest = { expanded2 = false },
                        ) {
                            DropdownMenuItem(
                                text = { Text("Add") },
                                onClick = {}
                            )
                        }
                    }


                }
            )
        },
        bottomBar = {
            BottomAppBar(

            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Points de vie : ")
                    Button(onClick = {}
                    ) { Text(text = "Attaquer") }
                }

            }
        }

    )


    { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth().weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Joueur")
                Text("90 points de vie")
                Text("10 forces")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth().weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painter = painterResource(R.drawable.caterpie), contentDescription = "pokemon")
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    MyApplicationTheme {
        Greeting()
    }
}