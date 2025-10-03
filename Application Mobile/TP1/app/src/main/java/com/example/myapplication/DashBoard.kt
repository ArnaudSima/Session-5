package com.example.myapplication

import android.content.Context
import android.graphics.drawable.shapes.Shape
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DashBoard(navController: NavController) {
    val context = LocalContext.current
    var estAffiche by remember { mutableStateOf(false) }

    class Vehicule(
        val modele: String,
        val kilometrage: Int,
        val image: Int,
        val son: Int,
        val prix: Int
    );
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
        Vehicule(
            modele = "1993 shadow vt1100",
            kilometrage = 70000,
            image = R.drawable.shadow,
            son = R.raw.pick_up_son,
            prix = 2000
        ),
    )
    LazyColumn {
        items(items = listeVehicules) { vehicule ->
            Column(modifier = Modifier.clickable(onClick = { estAffiche = true })) {
                Text(vehicule.modele)
                Text(vehicule.kilometrage.toString())
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(0.dp, 2.dp),
                    painter = painterResource(vehicule.image),
                    contentDescription = vehicule.modele,
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .border(1.dp, Color.Black, shape = CircleShape)
                        .size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "playButton"
                    )
                }
                HorizontalDivider(thickness = 5.dp, color = MaterialTheme.colorScheme.secondary)
            }


        }
    }
    PopUp(estAffiche, onChangeEstAffiche = { estAffiche = it })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUp(estAffiche: Boolean, onChangeEstAffiche: (Boolean) -> Unit) {
    if (estAffiche) {
        BasicAlertDialog(onDismissRequest = { onChangeEstAffiche(false) }) {
            Column (modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .width(200.dp)
                .height(400.dp)) {

            }
        }

    } else {
        return
    }
}