package com.example.myapplication.MiddleWare

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.Data.Vehicule.VehiculesDao
import com.example.myapplication.Data.VintageExpertBD
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VehiculeViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = "VehiculeViewModel"
    private val dao: VehiculesDao? =
        VintageExpertBD.getDatabase(application.applicationContext)?.VehiculesDao()

    fun add(vehicule: Vehicule) = viewModelScope.launch {
        dao?.insert(vehicule)
    }

    fun update(vehicule: Vehicule) = viewModelScope.launch {
        try {
            Log.d(TAG, "Update du vehicule en cours...")
            dao?.update(vehicule)
        } catch (illegalStateException: IllegalStateException) {
            Log.d(TAG, "Exception lors du update du vehicule : ${illegalStateException.message}")
        }

    }

    fun delete(vehicule: Vehicule) = viewModelScope.launch {
        try {
            Log.d(TAG, "Delete du vehicule en cours...")
            dao?.delete(vehicule)
            Log.d(TAG, "Delete effectué avec succès!")

        } catch (illegalStateException: IllegalStateException) {
            Log.d(TAG, "Exception lors du delete du vehicule : ${illegalStateException.message}")
        }
    }

    fun getVehiculeById(idSelectionne: Int): StateFlow<Vehicule?> =
        dao?.getVehiculeParId(idSelectionne)!!.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun getVehiculeDeUtilisateur(idSelectionne: Int): StateFlow<List<Vehicule>>? =
        dao?.getVehiculeDeUtilisateur(idSelectionne)!!.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}