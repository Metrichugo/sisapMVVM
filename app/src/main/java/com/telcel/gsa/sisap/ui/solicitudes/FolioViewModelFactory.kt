package com.telcel.gsa.sisap.ui.solicitudes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class FolioViewModelFactory(private val idEmployee : String ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FolioViewModel::class.java)){
            return FolioViewModel(idEmployee) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}