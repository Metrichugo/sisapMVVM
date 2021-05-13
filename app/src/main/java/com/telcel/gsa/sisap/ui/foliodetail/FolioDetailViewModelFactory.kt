package com.telcel.gsa.sisap.ui.foliodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class FolioDetailViewModelFactory(val idFolio: String, val idEmployee: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FolioDetailViewModel::class.java)){
            return FolioDetailViewModel(idFolio,idEmployee) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}