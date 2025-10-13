package com.example.myapplication.View.Pages

import android.content.ContentValues
import android.provider.MediaStore
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.MiddleWare.VehiculeViewModel

@Composable
fun AjoutVehicule(
    navController: NavController,
    vehiculeViewModel: VehiculeViewModel,
    idUtilisateur: Int
) {

    var titre by remember { mutableStateOf("") }
    var kilometrage by remember { mutableIntStateOf(0) }
    var prix by remember { mutableIntStateOf(0) }
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
                Toast.makeText(context, "Photo prise avec succès!", Toast.LENGTH_SHORT).show()
                image = photoUri.toString()
            }
        }
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                            gererInput(it, onChangeInput = {kilometrage = it},mauvaisInput)
                        }, modifier = Modifier.padding(5.dp, 0.dp))
                        TextField(value = "$prix", onValueChange = {
                            gererInput(it, onChangeInput = {prix = it},mauvaisInput)
                        }, modifier = Modifier.padding(5.dp, 0.dp))
                    }

                }


            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {

                Button(
                    onClick = {if (photoUri != null) {
                        takePicture.launch(photoUri!!)
                    }
                    }, modifier = Modifier
                ) {
                    Text("Ajouter Image")
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
                vehiculeViewModel.add(vehicule)

            }) { Text("Enregistrer") }
        }
    }

}
fun gererInput(input : String, onChangeInput : (Int) -> Unit, mauvaisInput : Toast){
    if(input.isEmpty()){
        return
    }
    if (input.isDigitsOnly()) {
       onChangeInput(input.toInt())
    } else {
        mauvaisInput.show()
    }
}
