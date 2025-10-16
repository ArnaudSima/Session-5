package com.example.myapplication.View.Pages

import android.content.Context
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.myapplication.Data.utilisateur.Utilisateur
import com.example.myapplication.MiddleWare.UtilisateurViewModel
import com.example.myapplication.R
import com.example.myapplication.View.Routes
import com.example.myapplication.View.navigationManuelle
import com.example.myapplication.View.routeActuelle

@Composable
fun Inscription(
    navController: NavController,
    viewModel: UtilisateurViewModel,
    onChangeIdUtilisateur: (Int) -> Unit
) {
    val motDePasse = remember { mutableStateOf("") }
    val nomUtilisateur = remember { mutableStateOf("") }
    val context = LocalContext.current


    val TAG = "Inscription"
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
                    text = stringResource(R.string.inscription),
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
                        stringResource(R.string.nom_utilisateur_inscription),
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
                        stringResource(R.string.mot_de_passe_inscription),
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
            content = { Text(stringResource(R.string.s_inscrire_inscription), fontSize = 20.sp) },
            onClick = {
                InscrireUtilisateur(
                    context,
                    nomUtilisateur.value,
                    viewModel,
                    onChangeIdUtilisateur = { onChangeIdUtilisateur(it) },
                    motDePasse.value
                )
            },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary
            ),

            )
    }
}

fun InscrireUtilisateur(
    context: Context,
    nomUtilisateur: String,
    viewModel: UtilisateurViewModel,
    onChangeIdUtilisateur: (Int) -> Unit,
    motDePasse: String
) {
    Log.d(
        TAG,
        context.getString(
            R.string.valeur_nom_utilisateur_inscription,
            nomUtilisateur
        )
    )
    Log.d(
        TAG,
        context.getString(R.string.valeur_mot_de_passe_inscription, motDePasse)
    )
    if (!motDePasse.isDigitsOnly() || motDePasse.isEmpty()) {
        Toast.makeText(
            context,
            context.getString(R.string.le_mot_de_passe_doit_etre_numeric),
            Toast.LENGTH_SHORT
        ).show()
        return
    }
    if (nomUtilisateur.isEmpty()) {
        Toast.makeText(
            context,
            context.getString(R.string.vous_devez_entrer_un_nom_d_utilisateur),
            Toast.LENGTH_SHORT
        ).show()
        return
    }

    val utilisateur = Utilisateur(nomUtilisateur = nomUtilisateur, motDePasse = motDePasse.toInt())
    viewModel.add(utilisateur)
    Log.d(TAG, context.getString(R.string.utilisateur_ajoute_a_la_base_de_donn_e))
    onChangeIdUtilisateur(utilisateur.idUtilisateur)
    Toast.makeText(
        context,
        context.getString(R.string.inscription_compl_t_avec_succ_s_connexion_en_cours),
        Toast.LENGTH_SHORT
    ).show()
    Log.d(TAG, context.getString(R.string.redirection_vers_dashboard))
    routeActuelle = Routes.DashBoard.route
    navigationManuelle = true
}