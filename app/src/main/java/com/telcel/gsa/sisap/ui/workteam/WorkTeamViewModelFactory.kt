package com.telcel.gsa.sisap.ui.workteam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.telcel.gsa.sisap.ui.network.Employee
import com.telcel.gsa.sisap.ui.network.FolioDetail
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class WorkTeamViewModelFactory(private val workTeamList: List<Employee>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WorkTeamViewModel::class.java)){
            return WorkTeamViewModel(workTeamList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}