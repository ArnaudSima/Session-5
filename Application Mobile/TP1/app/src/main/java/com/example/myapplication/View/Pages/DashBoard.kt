package com.example.myapplication.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.Data.utilisateur.Utilisateur
import com.example.myapplication.MiddleWare.VehiculeViewModel
import com.example.myapplication.View.Routes

@Composable
fun DashBoard(navController: NavController, onChangeIdVehicule: (Int) -> Unit, vehiculeViewModel: VehiculeViewModel,idUtilisateur: Int) {
    val listeVehiculesNullable = vehiculeViewModel.getVehiculeDeUtilisateur(idUtilisateur)?.collectAsState(initial = emptyList())?.value
    val listeVehicules : List<Vehicule>
    if(listeVehiculesNullable != null){
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
    listeVehicules : List<Vehicule>
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
                    painter = painterResource(vehicule.image),
                    contentDescription = vehicule.titre,
                    contentScale = ContentScale.Crop
                )

                HorizontalDivider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
            }


        }
    }
}

