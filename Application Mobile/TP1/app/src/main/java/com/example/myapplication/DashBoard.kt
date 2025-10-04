package com.example.myapplication

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.myapplication.Enums.ActionPossibleModification
import com.example.myapplication.Enums.EcransPageConnexion


var listeVehicules = listOf<Vehicules>(
    Vehicules(
        id = 1,
        titre = "1977 Ford Mustang Shelby",
        kilometrage = 120000,
        image = R.drawable.shelby,
        son = R.raw.shelby_son,
        prix = 12000
    ),
    Vehicules(
        id = 2,
        titre = "1980 Ford Bronco",
        kilometrage = 80000,
        image = R.drawable.bronco,
        son = R.raw.bronco_son,
        prix = 15000,

        ),
    Vehicules(
        id = 3,
        titre = "1982 chevrolet silverado",
        kilometrage = 120000,
        image = R.drawable.silverado,
        son = R.raw.pick_up_son,
        prix = 10000
    ),
    Vehicules(
        id = 4,
        titre = "1993 shadow vt1100",
        kilometrage = 70000,
        image = R.drawable.shadow,
        son = R.raw.pick_up_son,
        prix = 2000
    ),
)

@Composable
fun DashBoard(navController: NavController) {
    val context = LocalContext.current

    var idSelectionne by remember { mutableIntStateOf(1) }
    var ecransPageConnexion by remember { mutableStateOf(EcransPageConnexion.LISTE) }
    when (ecransPageConnexion) {
        EcransPageConnexion.MODIFICATION -> ModificationVehicule(
            idSelectionne, context
        )

        EcransPageConnexion.LISTE -> ListeVehicules(
            navController,
            onChangeId = { idSelectionne = it },
            onChangeEcransPageConnexion = { ecransPageConnexion = it })
    }

}

@Composable
fun ListeVehicules(
    navController: NavController,
    onChangeId: (Int) -> Unit,
    onChangeEcransPageConnexion: (EcransPageConnexion) -> Unit,
) {
    LazyColumn {
        items(items = listeVehicules) { vehicule ->
            Column(modifier = Modifier.clickable(onClick = {
                onChangeId(vehicule.id)
                onChangeEcransPageConnexion(EcransPageConnexion.MODIFICATION)
            })) {
                Text(vehicule.titre)
                Text(vehicule.kilometrage.toString())
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(0.dp, 2.dp),
                    painter = painterResource(vehicule.image),
                    contentDescription = vehicule.titre,
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .border(1.dp, Color.Black, shape = CircleShape)
                        .size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow, contentDescription = "playButton"
                    )
                }
                HorizontalDivider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
            }


        }
    }
}

@Composable
fun ModificationVehicule(idSelectionne: Int, context: Context) {
    val vehiculeAffiche = Helper.ChoisirVehiculeSelonId(idSelectionne)
    var Titre by remember { mutableStateOf(vehiculeAffiche.titre) }
    var Kilometrage by remember { mutableIntStateOf(vehiculeAffiche.kilometrage) }
    var Prix by remember { mutableStateOf(vehiculeAffiche.prix) }
    var Son by remember { mutableIntStateOf(vehiculeAffiche.son) }
    var Image by remember { mutableIntStateOf(vehiculeAffiche.image) }
    var ActionEnCours by remember { mutableStateOf(ActionPossibleModification.DEFAUT) }
    when (ActionEnCours) {
        ActionPossibleModification.DEFAUT ->
            ModeDefaut(vehiculeAffiche,context,onChangeActionEnCours = { ActionEnCours = it })

        ActionPossibleModification.MODIFICATION ->
            ModeModification(
                vehiculeAffiche, context, onChangeActionEnCours = { ActionEnCours = it })

        ActionPossibleModification.SUPPRESSION -> Text("Supprime")
    }

}

@Composable
fun ModeModification(
    vehiculeAffiche: Vehicules,
    context: Context,
    onChangeActionEnCours: (ActionPossibleModification) -> Unit,
) {
    var Titre by remember { mutableStateOf(vehiculeAffiche.titre) }
    var Kilometrage by remember { mutableIntStateOf(vehiculeAffiche.kilometrage) }
    var Prix by remember { mutableStateOf(vehiculeAffiche.prix) }
    var Image by remember { mutableIntStateOf(vehiculeAffiche.image) }
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
                    value = Titre,
                    onValueChange = { Titre = it },
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
                        TextField(value = "$Kilometrage", onValueChange = {
                            if (it.isDigitsOnly()) {
                                Kilometrage = it.toInt()
                            } else {
                                mauvaisInput.show()
                            }
                        }, modifier = Modifier.padding(5.dp, 0.dp))
                        TextField(value = "$Prix", onValueChange = {
                            if (it.isDigitsOnly()) {
                                Prix = it.toInt()
                            } else {
                                mauvaisInput.show()
                            }
                        }, modifier = Modifier.padding(5.dp, 0.dp))
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .border(1.dp, Color.Black, shape = CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "playButton",
                            modifier = Modifier.padding(5.dp, 0.dp)
                        )
                    }
                }


            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Image),
                    contentDescription = Titre,
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
            Button(onClick = { onChangeActionEnCours(ActionPossibleModification.DEFAUT) }) { Text("Enregistrer") }
            Button(onClick = { onChangeActionEnCours(ActionPossibleModification.DEFAUT) }) { Text("Annuler") }
        }
    }

}

@Composable
fun ModeDefaut(
    vehiculeAffiche: Vehicules,context: Context, onChangeActionEnCours: (ActionPossibleModification) -> Unit
) {
    var Titre by remember { mutableStateOf(vehiculeAffiche.titre) }
    var Kilometrage by remember { mutableIntStateOf(vehiculeAffiche.kilometrage) }
    var Prix by remember { mutableStateOf(vehiculeAffiche.prix) }
    var Image by remember { mutableIntStateOf(vehiculeAffiche.image) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {}) { Text("Retourner") }
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
                Text(Titre, modifier = Modifier.padding(0.dp, 5.dp))
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
                            "Kilometrage : $Kilometrage", modifier = Modifier.padding(5.dp, 0.dp)
                        )
                        Text("Prix : $Prix", modifier = Modifier.padding(5.dp, 0.dp))
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .border(1.dp, Color.Black, shape = CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "playButton",
                            modifier = Modifier.padding(5.dp, 0.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(Image),
                        contentDescription = Titre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }


        }
        Row {
            Button(onClick = {
                onChangeActionEnCours(ActionPossibleModification.MODIFICATION)
            }) { Text("Modifier") }
            Button(onClick = { onChangeActionEnCours(ActionPossibleModification.SUPPRESSION) }) {
                Text(
                    "Supprimer"
                )
            }
        }
    }
}
