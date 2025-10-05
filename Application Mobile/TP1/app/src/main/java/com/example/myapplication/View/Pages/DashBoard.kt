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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.View.Enums.EcransPageConnexion
import com.example.myapplication.View.Routes

@Composable
fun DashBoard(navController: NavController, onChangeId: (Int) -> Unit) {
    val context = LocalContext.current

    var idSelectionne by remember { mutableIntStateOf(1) }
    var ecransPageConnexion by remember { mutableStateOf(EcransPageConnexion.LISTE) }


    ListeVehicules(
        navController,
        onChangeId = { onChangeId(it) })


}

@Composable
fun ListeVehicules(
    navController: NavController,
    onChangeId: (Int) -> Unit,
) {
    LazyColumn {
        items(items = listeVehicules) { vehicule ->
            Column(modifier = Modifier.clickable(onClick = {
                navController.navigate(Routes.Modification.route)
                onChangeId(vehicule.id)
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

