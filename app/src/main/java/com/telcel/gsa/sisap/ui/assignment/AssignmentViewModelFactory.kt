package com.telcel.gsa.sisap.ui.assignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class AssignmentViewModelFactory(val idEmployee: String, val idFolio: String) :  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AssignmentViewModel::class.java)){
            return AssignmentViewModel(idEmployee,idFolio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}