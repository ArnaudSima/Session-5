package com.example.myapplication.MiddleWare

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.VintageExpertBD
import com.example.myapplication.Data.utilisateur.Utilisateur
import com.example.myapplication.Data.utilisateur.UtilisateurDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UtilisateurViewModel(application: Application) : AndroidViewModel(application) {
    private val dao: UtilisateurDao? =
        VintageExpertBD.getDatabase(application.applicationContext)?.UtilisateurDao()

    fun verifierIdentifiants(
        motDePasse: Int,
        nomUtilisateur: String,
    ): Utilisateur? {
        val utilisateur: Utilisateur? =
            dao?.verifierNomUtilisateurEtMotDePasse(motDePasse, nomUtilisateur)
        return utilisateur
    }
    val utilisateurs: StateFlow<List<Utilisateur>> =
        dao?.utilisateurs()!!.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )


    fun add(utilisateur: Utilisateur) = viewModelScope.launch {
            dao?.insert(utilisateur)

    }

    fun update(utilisateur: Utilisateur) = viewModelScope.launch {
            dao?.update(utilisateur)

    }

    fun delete(utilisateur: Utilisateur) = viewModelScope.launch {
            dao?.delete(utilisateur)

    }
}