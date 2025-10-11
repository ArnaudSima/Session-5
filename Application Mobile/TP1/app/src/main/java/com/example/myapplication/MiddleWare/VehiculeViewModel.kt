package com.example.myapplication.MiddleWare

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.Vehicule.Vehicule
import com.example.myapplication.Data.Vehicule.VehiculesDao
import com.example.myapplication.Data.VintageExpertBD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VehiculeViewModel(application: Application) : AndroidViewModel(application){
    private val dao : VehiculesDao? = VintageExpertBD.getDatabase(application.applicationContext)?.VehiculesDao()

    fun add(vehicule : Vehicule) = viewModelScope.launch {
        viewModelScope.launch(Dispatchers.IO) {
            dao?.insert(vehicule)
        }
    }
    fun update(vehicule: Vehicule) = viewModelScope.launch {
        viewModelScope.launch(Dispatchers.IO) {
            dao?.update(vehicule)
        }
    }
    fun delete(vehicule: Vehicule) = viewModelScope.launch {
        viewModelScope.launch(Dispatchers.IO) {
            dao?.delete(vehicule)
        }
    }
    fun getVehiculeById (idSelectionne : Int): StateFlow<Vehicule?> = dao?.getVehiculeParId(idSelectionne)!!.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
    fun getVehiculeDeUtilisateur(idSelectionne: Int): StateFlow<List<Vehicule>>? = dao?.getVehiculeDeUtilisateur(idSelectionne)!!.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}