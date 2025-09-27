package com.example.myapplication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)

    @Composable
    fun Connexion(navController: NavController)
    {
        val motDePasse = remember { mutableStateOf("") }
        val nomUtilisateur = remember { mutableStateOf("") }
        val messageUtilisateur =
            remember { mutableStateOf("Mauvais mot de passe ou nom d'utilisateur") }

        val context = LocalContext.current

        data class Utilisateur(val nomUtilisateur: String, val motDePasse: String)

        val TAG = "Connexion"
        val admin = Utilisateur("Admin", "123")
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Column(
                modifier = Modifier
                    .size(300.dp, 400.dp)
                    .border(2.dp, Color.LightGray, shape = RectangleShape)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RectangleShape
                    ),
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
                            onValueChange = {
                                nomUtilisateur.value = it
                            },
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
                            onValueChange = {
                                motDePasse.value = it
                            },
                            textStyle = LocalTextStyle.current.copy(fontSize = 28.sp),
                            visualTransformation = PasswordVisualTransformation(),
                            shape = RectangleShape

                        )
                    }

                }


            }

            Button(
                content = { Text("Se connecter", fontSize = 20.sp) }, onClick = {
                    Log.d(TAG, "Valeur nom utilisateur : ${nomUtilisateur.value}")
                    Log.d(TAG, "Valeur mot de passe : ${motDePasse.value}")
                    if (motDePasse.value == admin.motDePasse && nomUtilisateur.value == admin.nomUtilisateur) {
                        messageUtilisateur.value = "Connexion..."
                        navController.navigate(Ecrans.DashBoard.route)
                    } else {
                        messageUtilisateur.value = "Mauvais mot de passe ou nom d'utilisateur"
                    }
                    val toast = Toast.makeText(context, messageUtilisateur.value, Toast.LENGTH_SHORT)
                    toast.show()
                }, colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    }

