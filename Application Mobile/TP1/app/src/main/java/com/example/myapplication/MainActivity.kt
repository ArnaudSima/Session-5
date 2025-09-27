package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val fontFamily = FontFamily(Font(R.font.yesteryear, FontWeight.Normal))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val motDePasse = remember { mutableStateOf("") }
    val nomUtilisateur = remember { mutableStateOf("") }

    data class Utilisateur(val nomUtilisateur: String, val motDePasse: String)

    val admin = Utilisateur("Admin", "123")
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
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.secondary, shape = RectangleShape)
                    .size(300.dp, 400.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(2.dp, Color.LightGray, shape = RectangleShape),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .border(2.dp, Color.LightGray, shape = RectangleShape),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Connexion",
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(4f)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Nom utilisateur",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 10.dp),
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                            TextField(
                                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                                value = nomUtilisateur.value,
                                onValueChange = { nomUtilisateur.value = it },
                                shape = RectangleShape,
                                textStyle = LocalTextStyle.current.copy(fontSize = 28.sp),
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Mot de passe",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 10.dp),
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                            TextField(
                                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                                value = motDePasse.value,
                                onValueChange = { motDePasse.value = it },
                                textStyle = LocalTextStyle.current.copy(fontSize = 28.sp),
                                visualTransformation = PasswordVisualTransformation(),
                                shape = RectangleShape

                            )
                        }

                    }


                }
            }
            Button(
                content = { Text("Se connecter", fontSize = 20.sp) }, onClick = {
                if (motDePasse.value == admin.motDePasse && nomUtilisateur.value == admin.nomUtilisateur) {

                }else{

                }
            }, colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary
            )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Main()
}