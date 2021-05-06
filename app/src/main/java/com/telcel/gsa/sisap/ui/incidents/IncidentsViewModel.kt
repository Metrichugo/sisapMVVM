package com.telcel.gsa.sisap.ui.incidents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.ui.network.IncidenceRequest
import com.telcel.gsa.sisap.ui.network.IncidentsList
import com.telcel.gsa.sisap.ui.network.SisapApi
import kotlinx.coroutines.launch
import java.lang.Exception

class IncidentsViewModel(private val idEmployee: String) : ViewModel() {

    private val _incidents = MutableLiveData<IncidentsList>()
    val incidents : LiveData<IncidentsList>
    get() = _incidents

    init {
        getIncidents()
    }

    private fun getIncidents(){
        viewModelScope.launch{
            try {
                _incidents.value = SisapApi.retrofitService.getIncidents(IncidenceRequest(idEmployee))
            }catch (e: Exception){
                _incidents.value = IncidentsList(ArrayList())
            }
        }
    }
}