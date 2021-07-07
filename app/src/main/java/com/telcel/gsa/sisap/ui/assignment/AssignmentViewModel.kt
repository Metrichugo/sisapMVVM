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

    private val _allResources = MutableLiveData<Boolean>()
    val allResources : LiveData<Boolean>
        get() = _allResources

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

    fun setAllResourcesSelected(){
        _teamMembersList.value?.let {
            it.TeamMemberList.forEach { teamMember ->
                teamMember.isAssignedAsDeveloper = !teamMember.isAssignedAsDeveloper
            }
            if (_allResources.value != null){
                _allResources.value?.let { allResources->
                    _allResources.value = !allResources
                }
            }else{
                _allResources.value = true
            }
            _enableClick.value = _allResources.value != null && _projectManager.value != null
        }
    }

    fun postAssignment(){
        var involvedEmployees = ArrayList<InvolvedEmployee>()
        _teamMembersList.value?.let {
            if (_allResources.value != null){
                _allResources.value?.let { _->
                    it.TeamMemberList.forEach { member ->
                        val involvedEmployee = InvolvedEmployee(member.idEmployee,member.isAssignedAsLeader,member.isAssignedAsDeveloper)
                        involvedEmployees.add(involvedEmployee)
                    }
                }
            }else{
                val(assigned,_) = it.TeamMemberList.partition { member ->
                    member.isAssignedAsDeveloper
                }
                assigned.forEach { member ->
                    val involvedEmployee = InvolvedEmployee(member.idEmployee,member.isAssignedAsLeader,member.isAssignedAsDeveloper)
                    involvedEmployees.add(involvedEmployee)
                }
            }
            _projectManager.let { leader->
                val involvedEmployee = involvedEmployees.find { employee->
                    employee.idEmployee == leader.value?.idEmployee
                }?.also { founded ->
                    founded.isLeader = true
                }
                if(involvedEmployee == null){
                    involvedEmployees.add(InvolvedEmployee(leader.value!!.idEmployee,true,true))
                }
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