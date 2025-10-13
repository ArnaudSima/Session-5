package com.example.myapplication.View.Pages

import android.R
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.Data.utilisateur.Utilisateur
import com.example.myapplication.MiddleWare.VehiculeViewModel
import com.example.myapplication.View.Enums.BottomBar
import com.example.myapplication.View.Routes
 val TAG = "Dashboard"
@Composable
fun DashBoard(
    navController: NavController,
    onChangeIdVehicule: (Int) -> Unit,
    vehiculeViewModel: VehiculeViewModel,
    idUtilisateur: Int,
) {
    val listeVehiculesNullable = vehiculeViewModel.getVehiculeDeUtilisateur(idUtilisateur)
        ?.collectAsState(initial = emptyList())?.value
    val listeVehicules: List<Vehicule>
    if (listeVehiculesNullable != null) {
        listeVehicules = listeVehiculesNullable
        ListeVehicules(
            navController,
            onChangeIdVehicule = { onChangeIdVehicule(it) },
            listeVehicules
        )
    }


}


@Composable
fun ListeVehicules(
    navController: NavController,
    onChangeIdVehicule: (Int) -> Unit,
    listeVehicules: List<Vehicule>
) {
    LazyColumn {
        items(items = listeVehicules) { vehicule ->
            Column(modifier = Modifier.clickable(onClick = {
                navController.navigate(Routes.Modification.route)
                onChangeIdVehicule(vehicule.idVehicule)
            })) {
                Text(vehicule.titre)
                Text(vehicule.kilometrage.toString())
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(0.dp, 2.dp),
                    painter = rememberAsyncImagePainter(
                        model = vehicule.image
                    ),
                    contentDescription = vehicule.titre,
                    contentScale = ContentScale.Crop
                )

                HorizontalDivider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
            }


        }
    }
    BottomBarDashBoard(navController)
}

@Composable
fun BottomBarDashBoard(
    navController: NavController,
) {
    return BottomAppBar(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                Log.d(TAG,"Envoie sur ecran ajout")
                navController.navigate(Routes.Ajout.route) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "ajouterVehicule",
                    modifier = Modifier.border(
                        width = 3.dp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        shape = CircleShape
                    )
                )
            }
        }
    }
}

