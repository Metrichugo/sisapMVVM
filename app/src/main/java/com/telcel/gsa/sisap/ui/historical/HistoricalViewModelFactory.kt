package com.telcel.gsa.sisap.ui.historical

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class HistoricalViewModelFactory (private val idFolio : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HistoricalViewModel::class.java)){
            return HistoricalViewModel(idFolio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}