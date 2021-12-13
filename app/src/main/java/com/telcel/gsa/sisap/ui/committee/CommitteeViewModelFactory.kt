package com.telcel.gsa.sisap.ui.committee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class CommitteeViewModelFactory(private val idFolio : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommitteeViewModel::class.java)){
            return CommitteeViewModel(idFolio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}