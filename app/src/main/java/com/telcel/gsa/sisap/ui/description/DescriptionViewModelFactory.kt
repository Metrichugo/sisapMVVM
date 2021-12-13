package com.telcel.gsa.sisap.ui.description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.telcel.gsa.sisap.ui.network.FolioDetail
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class DescriptionViewModelFactory(private val detail: FolioDetail) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DescriptionViewModel::class.java)){
            return DescriptionViewModel(detail) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}