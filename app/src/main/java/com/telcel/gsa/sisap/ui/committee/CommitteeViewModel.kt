package com.telcel.gsa.sisap.ui.committee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.ui.network.CommitteeMembersList
import com.telcel.gsa.sisap.ui.network.CommitteeRequest
import com.telcel.gsa.sisap.ui.network.SisapApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class CommitteeViewModel(private val idFolio : Int) : ViewModel() {

    private val _committee = MutableLiveData<CommitteeMembersList>()
    val committee : LiveData<CommitteeMembersList>
    get() = _committee


    init {
        getCommitteeMembers()
    }

    fun getCommitteeMembers(){
        viewModelScope.launch {
            try {
                _committee.value = SisapApi.retrofitService.getCommitteeMembers(CommitteeRequest(idFolio.toString()))
            }catch (e: Exception){
                _committee.value = CommitteeMembersList(ArrayList())
            }
        }
    }

}