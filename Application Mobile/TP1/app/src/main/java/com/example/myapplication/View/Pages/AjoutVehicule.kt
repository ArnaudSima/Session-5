package com.example.myapplication.View.Pages

import android.R
import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.MiddleWare.UtilisateurViewModel
import com.example.myapplication.MiddleWare.VehiculeViewModel
import com.example.myapplication.View.Routes
import com.example.myapplication.View.navigationManuelle
import com.example.myapplication.View.routeActuelle

@Composable
fun AjoutVehicule(
    navController: NavController,
    vehiculeViewModel: VehiculeViewModel,
    idUtilisateur: Int
) {

    var titre by remember { mutableStateOf("") }
    var kilometrage by remember { mutableStateOf("") }
    var prix by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    val context = LocalContext.current
    val photoUri by remember {
        mutableStateOf(
            context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ContentValues().apply {
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    put(
                        MediaStore.Images.Media.DISPLAY_NAME,
                        "photo_${System.currentTimeMillis()}.jpg"
                    )
                }
            )
        )
    }
    val takePicture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                Toast.makeText(context, context.getString(com.example.myapplication.R.string.photo_prise_avec_succ_s), Toast.LENGTH_SHORT).show()
                image = photoUri.toString()
            }
        }
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                            kilometrage = it
                        }, modifier = Modifier.padding(5.dp, 0.dp))
                        TextField(value = "$prix", onValueChange = {
                           prix = it
                        }, modifier = Modifier.padding(5.dp, 0.dp))
                    }

                }


            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = titre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    placeholder = painterResource(id = R.drawable.ic_menu_report_image),
                    error = painterResource(id = R.drawable.ic_menu_report_image)
                    )
                Button(
                    onClick = { if (photoUri != null) {
                        takePicture.launch(photoUri!!)
                    }}, modifier = Modifier
                ) {
                    Text(stringResource(com.example.myapplication.R.string.ajouter_image))
                }
            }



        }
        Row {
            Button(onClick = {
                AjouterVehicule(titre,kilometrage,image,prix,idUtilisateur,context,vehiculeViewModel)
                routeActuelle = Routes.DashBoard.route
                navigationManuelle = true
                Toast.makeText(context,
                    context.getString(com.example.myapplication.R.string.vehicule_ajoute_avec_succes), Toast.LENGTH_SHORT).show()
            }) { Text(stringResource(com.example.myapplication.R.string.enregistrer)) }
        }
    }

}

fun AjouterVehicule(titre : String, kilometrage : String, image : String,prix : String,idUtilisateur : Int,context : Context,vehiculeViewModel: VehiculeViewModel){
    if (!kilometrage.isDigitsOnly() || kilometrage.isEmpty()){
        Toast.makeText(context, "Le kilometrage doit etre un entier numerique", Toast.LENGTH_SHORT).show()
        return
    }
    if(!prix.isDigitsOnly() || prix.isEmpty()){
        Toast.makeText(context, "Le prix doit etre un entier numerique", Toast.LENGTH_SHORT).show()
        return
    }
    if(image.isEmpty()){
        Toast.makeText(context, "Il manque une image", Toast.LENGTH_SHORT).show()
        return
    }
    val vehicule = Vehicule(
        titre = titre,
        kilometrage = kilometrage.toInt(),
        image = image,
        prix = prix.toInt(),
        idUtilisateur = idUtilisateur
    )
    Log.d(TAG,vehicule.toString())
    vehiculeViewModel.add(vehicule)
}
