package com.example.myapplication

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DashBoard(navController: NavController) {
    class Vehicule(val modele: String, val kilometrage: Int, val image: Int, val son: Int, val prix : Int);
    val listeVehicules = listOf<Vehicule>(
        Vehicule(
            modele = "1977 Ford Mustang Shelby",
            kilometrage = 120000,
            image = R.drawable.shelby,
            son = R.raw.shelby_son,
            prix = 12000
        ),
        Vehicule(
            modele = "1980 Ford Bronco",
            kilometrage = 80000,
            image = R.drawable.bronco,
            son = R.raw.bronco_son,
            prix = 15000,

        ),
        Vehicule(
            modele = "1982 chevrolet silverado",
            kilometrage = 120000,
            image = R.drawable.silverado,
            son = R.raw.pick_up_son,
            prix = 10000
        ),
    )
    LazyColumn {
        items(items = listeVehicules) { vehicule ->
            Column {
                Text(vehicule.modele)
                Text(vehicule.kilometrage.toString())
                Image(
                    painter = painterResource(vehicule.image),
                    contentDescription = vehicule.modele
                )
                IconButton(onClick = {},
                    modifier = Modifier
                        .border(1.dp, Color.Black, shape = CircleShape).size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "playButton"
                    )
                }
                HorizontalDivider()
            }


        }
    }
}