package com.telcel.gsa.sisap.ui.foliodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.ui.network.FolioDetail
import com.telcel.gsa.sisap.ui.network.FolioDetailRequest
import com.telcel.gsa.sisap.ui.network.SisapApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class FolioDetailViewModel(private val idFolio: String, private val idEmployee: String) : ViewModel() {
    val defaultDetailFolio = FolioDetail(idFolio = 0 , systemString = "-", description = "-", status = "-",complexity=0,priority=0,date="01/01/1990 00:00:00",isAssigned = true, sox = "-",title = "-",flowControl = null,estimatedTime =  0 , involvedEmployees = listOf())
    private val _detailFolio = MutableLiveData<FolioDetail?>()
    val detailFolio : LiveData<FolioDetail?>
        get() = _detailFolio

    init {
        getDetailFolio()
    }

    private fun getDetailFolio(){
        viewModelScope.launch {
            try{
                _detailFolio.value = SisapApi.retrofitService.getDetailFolio(FolioDetailRequest(idFolio,idEmployee))
            }catch (e: Exception){
                _detailFolio.value = defaultDetailFolio
            }
        }
    }
}