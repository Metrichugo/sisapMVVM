package com.telcel.gsa.sisap.ui.documents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class FolioDocumentsViewModelFactory(private val idFolio : String ,private val idEmployee: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FolioDocumentsViewModel::class.java)) {
            return FolioDocumentsViewModel(idFolio, idEmployee) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}