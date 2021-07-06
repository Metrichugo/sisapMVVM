package com.telcel.gsa.sisap.ui.assignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.ui.network.*
import kotlinx.coroutines.launch
import java.lang.Exception

class AssignmentViewModel(private val idEmployee: String, private val idFolio: String) : ViewModel() {

    private val _teamMembersList = MutableLiveData<TeamMemberList>()
    val teamMemberList : LiveData<TeamMemberList>
    get() = _teamMembersList

    private val _projectManager = MutableLiveData<TeamMember>()
    val projectManager : LiveData<TeamMember>
        get() = _projectManager

    private val _enableClick = MutableLiveData<Boolean>()
    val enableClick : LiveData<Boolean>
        get() = _enableClick

    private val _assignmentAction = MutableLiveData<GenericPostAppResponse?>()
    val assignmentAction : LiveData<GenericPostAppResponse?>
        get() = _assignmentAction

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
        _enableClick.value = atLeastOneIsSelected() && _projectManager.value != null
    }

    fun setProjectManagerSelected(teamMember: TeamMember){
        _projectManager.value = teamMember
        _enableClick.value = atLeastOneIsSelected()
    }

    fun postAssignment(){
        var involvedEmployees = ArrayList<InvolvedEmployee>()
        _teamMembersList.value?.let {
            val(assigned,_) = it.TeamMemberList.partition { member ->
                member.isAssignedAsDeveloper
            }
            assigned.forEach { member ->
               val involvedEmployee = InvolvedEmployee(member.idEmployee,member.isAssignedAsLeader,member.isAssignedAsDeveloper)
                involvedEmployees.add(involvedEmployee)
            }
            _projectManager.let { leader->
                involvedEmployees.add(InvolvedEmployee(leader.value!!.idEmployee,true,true))
            }
        }

        viewModelScope.launch {
            try{
                _assignmentAction.value = SisapApi.retrofitService.postAssignmentResources(
                    AssignmentRequest(
                        SolicitudSimpleTO(idFolio),
                        AssignmentFolioTO(idFolio,involvedEmployees),
                        LoggedUserTO(idEmployee)
                    )
                )
            }catch (e: Exception){
                _assignmentAction.value = null
            }
        }

    }

    private fun atLeastOneIsSelected() : Boolean{
        var atLeastOneResourceSelected = false
        _teamMembersList.value?.let {
            val(assigned,_) = it.TeamMemberList.partition { member ->
                member.isAssignedAsDeveloper
            }
            if(assigned.isNotEmpty()){
                atLeastOneResourceSelected = true
            }
        }
        return atLeastOneResourceSelected
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