package com.example.myapplication.View.Pages

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.Data.utilisateur.Utilisateur
import com.example.myapplication.MiddleWare.VehiculeViewModel
import com.example.myapplication.R
import com.example.myapplication.View.Enums.ActionPossibleModification
import com.example.myapplication.View.Routes

@Composable
fun AffichageVehiculeSpecifique(
    navController: NavController,
    vehiculeViewModel: VehiculeViewModel,
    idVehiculeAffiche: Int,
    idUtilisateur: Int
) {
    var actionEnCours by remember { mutableStateOf(ActionPossibleModification.DEFAUT) }
    val context = LocalContext.current
    if (actionEnCours == ActionPossibleModification.SUPPRESSION) {
        ModeSuppression()
        return
    }
    val vehiculeAfficheFlow = remember(idVehiculeAffiche) {
        vehiculeViewModel.getVehiculeById(idVehiculeAffiche)
    }

    val vehiculeAfficheNullable = vehiculeAfficheFlow.collectAsState(initial = null).value
    var vehiculeAffiche: Vehicule
    if (vehiculeAfficheNullable != null) {
        vehiculeAffiche = vehiculeAfficheNullable
    } else {
        return
    }


    if (actionEnCours == ActionPossibleModification.DEFAUT) {
        ModeDefaut(
            vehiculeAffiche,
            onChangeActionEnCours = { actionEnCours = it },
            vehiculeViewModel,
            context
        )
    } else if (actionEnCours == ActionPossibleModification.MODIFICATION) {
        ModeModification(
            vehiculeAffiche,
            context,
            onChangeActionEnCours = { actionEnCours = it },
            vehiculeViewModel,
            idUtilisateur
        )
    }


}

@Composable
fun ModeModification(
    vehiculeAffiche: Vehicule,
    context: Context,
    onChangeActionEnCours: (ActionPossibleModification) -> Unit,
    vehiculeViewModel: VehiculeViewModel,
    idUtilisateur: Int
) {
    var titre by remember { mutableStateOf(vehiculeAffiche.titre) }
    var kilometrage by remember { mutableStateOf(vehiculeAffiche.kilometrage.toString()) }
    var prix by remember { mutableStateOf(vehiculeAffiche.prix.toString()) }
    var image by remember { mutableStateOf(vehiculeAffiche.image) }
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
                Toast.makeText(context,
                    context.getString(R.string.photo_prise_avec_succ_s), Toast.LENGTH_SHORT).show()
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
                        TextField(value = kilometrage, onValueChange = {
                            kilometrage = it
                        }, modifier = Modifier.padding(5.dp, 0.dp))
                        TextField(value = prix, onValueChange = {
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
                        .alpha(0.5f)
                )
                Button(
                    onClick = { if (photoUri != null) {
                        takePicture.launch(photoUri!!)
                    }

                    }, modifier = Modifier
                ) {
                    Text(stringResource(R.string.modifier_image))
                }
            }

        }
        Row {
            Button(onClick = {
                ModifierVehicule(vehiculeAffiche.idVehicule, titre,kilometrage,image,prix,idUtilisateur,context,vehiculeViewModel)
                onChangeActionEnCours(ActionPossibleModification.DEFAUT)
            }) { Text(stringResource(R.string.enregistrer_vehicule)) }
            Button(onClick = { onChangeActionEnCours(ActionPossibleModification.DEFAUT) }) {
                Text(
                    stringResource(R.string.annuler)
                )
            }
        }
    }

}

@Composable

fun ModeDefaut(
    vehiculeAffiche: Vehicule,
    onChangeActionEnCours: (ActionPossibleModification) -> Unit,
    vehiculeViewModel: VehiculeViewModel,
    context : Context
) {
    var titre by remember { mutableStateOf(vehiculeAffiche.titre) }
    var kilometrage by remember { mutableIntStateOf(vehiculeAffiche.kilometrage) }
    var prix by remember { mutableIntStateOf(vehiculeAffiche.prix) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(titre, modifier = Modifier.padding(0.dp, 5.dp))
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
                            stringResource(R.string.kilometrage, kilometrage),
                            modifier = Modifier.padding(5.dp, 0.dp)
                        )
                        Text(
                            stringResource(R.string.prix, prix),
                            modifier = Modifier.padding(5.dp, 0.dp)
                        )
                    }

                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = vehiculeAffiche.image,
                        contentDescription = titre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }


        }
        Row {
            Button(
                onClick = {
                    onChangeActionEnCours(ActionPossibleModification.MODIFICATION)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) { Text(stringResource(R.string.modifier)) }
            Button(
                onClick = {
                    vehiculeViewModel.delete(vehicule = vehiculeAffiche)
                    onChangeActionEnCours(ActionPossibleModification.SUPPRESSION)
                    Toast.makeText(context,
                        context.getString(R.string.entree_supprim_avec_succ_s), Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    stringResource(R.string.supprimer)
                )
            }
        }
    }

}

@Composable
fun ModeSuppression() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { Text(stringResource(R.string.vehicule_supprime)) }
}

fun ModifierVehicule(
    idVehicule: Int,
    titre: String,
    kilometrage: String,
    image: String,
    prix: String,
    idUtilisateur: Int,
    context: Context,
    vehiculeViewModel: VehiculeViewModel
) {
    if (!kilometrage.isDigitsOnly() || kilometrage.isEmpty()) {
        Toast.makeText(context,
            context.getString(R.string.le_kilometrage_doit_etre_un_entier_numerique), Toast.LENGTH_SHORT)
            .show()
        return
    }
    if (!prix.isDigitsOnly() || prix.isEmpty()) {
        Toast.makeText(context,
            context.getString(R.string.le_prix_doit_etre_un_entier_numerique), Toast.LENGTH_SHORT).show()
        return
    }
    if (image.isEmpty()) {
        Toast.makeText(context, context.getString(R.string.il_manque_une_image), Toast.LENGTH_SHORT).show()
        return
    }

    val vehicule = Vehicule(
        idVehicule = idVehicule,
        titre = titre,
        kilometrage = kilometrage.toInt(),
        image = image,
        prix = prix.toInt(),
        idUtilisateur = idUtilisateur
    )
    Log.d("VehiculeSpecifique","${vehicule.idVehicule}${vehicule.kilometrage}${vehicule.image}${vehicule.prix}${vehicule.idUtilisateur}")
    Toast.makeText(context,
        context.getString(R.string.vehicule_modifi_avec_succ_s), Toast.LENGTH_SHORT).show()
    vehiculeViewModel.update(vehicule)
}