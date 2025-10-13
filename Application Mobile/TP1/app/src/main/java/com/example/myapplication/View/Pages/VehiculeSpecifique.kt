package com.example.myapplication.View.Pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.Data.utilisateur.Utilisateur
import com.example.myapplication.MiddleWare.VehiculeViewModel
import com.example.myapplication.View.Enums.ActionPossibleModification
import com.example.myapplication.View.Routes

@Composable
fun AffichageVehiculeSpecifique(
    navController: NavController,
    vehiculeViewModel: VehiculeViewModel,
    idVehiculeAffiche: Int,
    idUtilisateur: Int
) {
    var actionEnCours by remember { mutableStateOf(ActionPossibleModification.DEFAUT) }
    val context = LocalContext.current
    val vehiculeAfficheNullable =
        vehiculeViewModel.getVehiculeById(idVehiculeAffiche).collectAsState(initial = null).value

    if (vehiculeAfficheNullable == null) {
        return
    }
    val vehiculeAffiche : Vehicule = vehiculeAfficheNullable
    when (actionEnCours) {
        ActionPossibleModification.DEFAUT ->
            ModeDefaut(
                navController,
                vehiculeAffiche,
                context,
                onChangeActionEnCours = { actionEnCours = it },
                vehiculeViewModel
            )

        ActionPossibleModification.MODIFICATION ->
            ModeModification(
                vehiculeAffiche,
                context,
                onChangeActionEnCours = { actionEnCours = it },
                vehiculeViewModel,
                idUtilisateur
            )

        ActionPossibleModification.SUPPRESSION -> ModeSuppression()
    }

}

@Composable
fun ModeModification(
    vehiculeAffiche: Vehicule,
    context: Context,
    onChangeActionEnCours: (ActionPossibleModification) -> Unit,
    vehiculeViewModel: VehiculeViewModel,
    idUtilisateur: Int
) {
    var titre by remember { mutableStateOf(vehiculeAffiche.titre) }
    var kilometrage by remember { mutableIntStateOf(vehiculeAffiche.kilometrage) }
    var prix by remember { mutableIntStateOf(vehiculeAffiche.prix) }
    var image by remember { mutableStateOf(vehiculeAffiche.image) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //a finir negro
        val mauvaisInput =
            Toast.makeText(context, "Le placeholder doit etre un entier!", Toast.LENGTH_SHORT)
        Column(
            Modifier
                .border(
                    2.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(5.dp)
                )
                .width(300.dp)
                .height(350.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 3.dp, color = MaterialTheme.colorScheme.secondary),
            ) {
                TextField(
                    value = titre,
                    onValueChange = { titre = it },
                    modifier = Modifier.padding(0.dp, 5.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        TextField(value = "$kilometrage", onValueChange = {
                            if (it.isDigitsOnly()) {
                                kilometrage = it.toInt()
                            } else {
                                mauvaisInput.show()
                            }
                        }, modifier = Modifier.padding(5.dp, 0.dp))
                        TextField(value = "$prix", onValueChange = {
                            if (it.isDigitsOnly()) {
                                prix = it.toInt()
                            } else {
                                mauvaisInput.show()
                            }
                        }, modifier = Modifier.padding(5.dp, 0.dp))
                    }

                }


            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = vehiculeAffiche.image
                    ),
                    contentDescription = titre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f)
                )
                Button(
                    onClick = { }, modifier = Modifier
                ) {
                    Text("Modifier Image")
                }
            }

        }
        Row {
            Button(onClick = {
                val vehicule = Vehicule(
                    titre = titre,
                    kilometrage = kilometrage,
                    image = image,
                    prix = prix,
                    idUtilisateur = idUtilisateur
                )
                vehiculeViewModel.update(vehicule)
                onChangeActionEnCours(ActionPossibleModification.DEFAUT)
            }) { Text("Enregistrer") }
            Button(onClick = { onChangeActionEnCours(ActionPossibleModification.DEFAUT) }) { Text("Annuler") }
        }
    }

}

@Composable

fun ModeDefaut(
    navController: NavController,
    vehiculeAffiche: Vehicule,
    context: Context,
    onChangeActionEnCours: (ActionPossibleModification) -> Unit,
    vehiculeViewModel: VehiculeViewModel
) {
    var titre by remember { mutableStateOf(vehiculeAffiche.titre) }
    var kilometrage by remember { mutableIntStateOf(vehiculeAffiche.kilometrage) }
    var prix by remember { mutableIntStateOf(vehiculeAffiche.prix) }
    var image by remember { mutableStateOf(vehiculeAffiche.image) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Button(
                onClick = { navController.navigate(Routes.DashBoard.route) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) { Text("Retourner") }
        }
        Column(
            Modifier
                .border(
                    2.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(5.dp)
                )
                .width(300.dp)
                .height(350.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 3.dp, color = MaterialTheme.colorScheme.secondary),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(titre, modifier = Modifier.padding(0.dp, 5.dp))
            }

            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(
                            "Kilometrage : $kilometrage", modifier = Modifier.padding(5.dp, 0.dp)
                        )
                        Text("Prix : $prix", modifier = Modifier.padding(5.dp, 0.dp))
                    }

                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = vehiculeAffiche.image
                        ),
                        contentDescription = titre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }


        }
        Row {
            Button(
                onClick = {
                    onChangeActionEnCours(ActionPossibleModification.MODIFICATION)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) { Text("Modifier") }
            Button(
                onClick = {
                    onChangeActionEnCours(ActionPossibleModification.SUPPRESSION)
                    vehiculeViewModel.delete(vehiculeAffiche)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    "Supprimer"
                )
            }
        }
    }

}

@Composable
fun ModeSuppression() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { Text("Vehicule supprime") }
}