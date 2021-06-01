package com.telcel.gsa.sisap.ui.classification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException


class ClassificationViewModelFactory(val idEmployee: String, val idFolio: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ClassificationViewModel::class.java)){
            return ClassificationViewModel(idEmployee,idFolio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}