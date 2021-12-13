package com.telcel.gsa.sisap.ui.folio

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class FolioViewModelFactory(private val idEmployee : String, private val application: Application ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FolioViewModel::class.java)){
            return FolioViewModel(idEmployee, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}