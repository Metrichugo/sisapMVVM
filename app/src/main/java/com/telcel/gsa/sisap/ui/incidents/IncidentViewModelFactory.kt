package com.telcel.gsa.sisap.ui.incidents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class IncidentViewModelFactory(private val idEmployee: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(IncidentsViewModel::class.java)){
            return IncidentsViewModel(idEmployee) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}