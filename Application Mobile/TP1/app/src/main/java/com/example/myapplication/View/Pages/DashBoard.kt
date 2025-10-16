package com.example.myapplication.View.Pages

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.MiddleWare.VehiculeViewModel
import com.example.myapplication.View.Routes
import com.example.myapplication.View.navigationManuelle
import com.example.myapplication.View.routeActuelle

val TAG = "Dashboard"

@Composable
fun DashBoard(
    navController: NavController,
    onChangeIdVehicule: (Int) -> Unit,
    vehiculeViewModel: VehiculeViewModel,
    idUtilisateur: Int,
) {

    val flowVehicules = remember(idUtilisateur) {
        vehiculeViewModel.getVehiculeDeUtilisateur(idUtilisateur)
    }

    val listeVehicules = flowVehicules?.collectAsState(initial = emptyList())?.value ?: emptyList()

    ListeVehicules(
        onChangeIdVehicule = { onChangeIdVehicule(it) },
        listeVehicules
    )
}


@Composable
fun ListeVehicules(
    onChangeIdVehicule: (Int) -> Unit,
    listeVehicules: List<Vehicule>
) {
    val context = LocalContext.current
    LazyColumn {
        items(items = listeVehicules) { vehicule ->
            Column(modifier = Modifier.clickable(onClick = {
                onChangeIdVehicule(vehicule.idVehicule)
                routeActuelle = Routes.Modification.route
                navigationManuelle = true
            })) {
                Text(context.getString(com.example.myapplication.R.string.modele, vehicule.titre))
                Text(
                    context.getString(
                        com.example.myapplication.R.string.kilometrage_km,
                        vehicule.kilometrage
                    ))
                Text(
                    context.getString(
                        com.example.myapplication.R.string.prix_dashboard,
                        vehicule.prix
                    ))

                AsyncImage(
                    model = vehicule.image,
                    contentDescription = vehicule.titre,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_menu_report_image),
                    error = painterResource(id = R.drawable.ic_delete)
                )


                HorizontalDivider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
            }
        }

    }

}



