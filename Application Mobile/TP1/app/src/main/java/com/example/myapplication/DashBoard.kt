package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


var listeVehicules = listOf<Vehicule>(
    Vehicule(
        id = 1,
        titre = "1977 Ford Mustang Shelby",
        kilometrage = 120000,
        image = R.drawable.shelby,
        son = R.raw.shelby_son,
        prix = 12000
    ),
    Vehicule(
        id = 2,
        titre = "1980 Ford Bronco",
        kilometrage = 80000,
        image = R.drawable.bronco,
        son = R.raw.bronco_son,
        prix = 15000,

        ),
    Vehicule(
        id = 3,
        titre = "1982 chevrolet silverado",
        kilometrage = 120000,
        image = R.drawable.silverado,
        son = R.raw.pick_up_son,
        prix = 10000
    ),
    Vehicule(
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
    var idSelectionne by remember { mutableIntStateOf(1) }
    var ecransPageConnexion by remember { mutableStateOf(EcransPageConnexion.LISTE) }
    when (ecransPageConnexion) {
        EcransPageConnexion.MODIFICATION -> ModificationVehicule(
            Helper.ChoisirVehiculeSelonId(
                idSelectionne
            )
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
    onChangeEcransPageConnexion: (EcransPageConnexion) -> Unit
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
fun ModificationVehicule(vehiculeAffiche: Vehicule) {

    var Titre by remember { mutableStateOf(vehiculeAffiche.titre) }
    var Kilometrage by remember { mutableIntStateOf(vehiculeAffiche.kilometrage) }
    var Prix by remember { mutableStateOf(vehiculeAffiche.prix) }
    var Son by remember { mutableIntStateOf(vehiculeAffiche.son) }
    var Image by remember { mutableIntStateOf(vehiculeAffiche.image) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(5.dp)
                )
                .width(300.dp)
                .height(350.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(Titre)
            }
            Row {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Kilometrage : $Kilometrage")
                    Text("Prix : $Prix")
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
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    ) {
                        Image(
                            painter = painterResource(vehiculeAffiche.image),
                            contentDescription = vehiculeAffiche.titre,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }


        }

    }
}
