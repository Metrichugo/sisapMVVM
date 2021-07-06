package com.telcel.gsa.sisap.ui.assignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.ui.network.MembersTeamRequest
import com.telcel.gsa.sisap.ui.network.SisapApi
import com.telcel.gsa.sisap.ui.network.TeamMember
import com.telcel.gsa.sisap.ui.network.TeamMemberList
import kotlinx.coroutines.launch
import java.lang.Exception

class AssignmentViewModel(private val idEmployee: String, private val idFolio: String) : ViewModel() {

    private val _teamMembersList = MutableLiveData<TeamMemberList>()
    val teamMemberList : LiveData<TeamMemberList>
    get() = _teamMembersList

    private val _projectManager = MutableLiveData<TeamMember>()
    val projectManager : LiveData<TeamMember>
        get() = _projectManager

    init {
        getTeamMemberList()
    }

    fun setSelectedResource(idEmployee : String){
        _teamMembersList.value?.let {
            it.TeamMemberList.find { member ->
                member.idEmployee == idEmployee
            }?.let { employee ->
                employee.isAssignedAsDeveloper = !employee.isAssignedAsDeveloper
            }
        }
    }

    fun setProjectManagerSelected(teamMember: TeamMember){
        _projectManager.value = teamMember
    }

    private fun getTeamMemberList(){
        viewModelScope.launch {
            try{
                _teamMembersList.value = SisapApi.retrofitService.getMembersTeam(MembersTeamRequest(idEmployee))
            }catch (e : Exception){
                _teamMembersList.value = TeamMemberList(ArrayList())
            }
        }
    }

}