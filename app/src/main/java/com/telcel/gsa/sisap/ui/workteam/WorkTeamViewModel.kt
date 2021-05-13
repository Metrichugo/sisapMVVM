package com.telcel.gsa.sisap.ui.workteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.telcel.gsa.sisap.ui.network.Employee
import com.telcel.gsa.sisap.ui.network.FolioDetail

class WorkTeamViewModel(private val workTeam: List<Employee>): ViewModel() {

    private val _workTeamList = MutableLiveData<List<Employee>>()
    val workTeamList : LiveData<List<Employee>>
    get() = _workTeamList

    init {
        _workTeamList.value = workTeam
    }
}