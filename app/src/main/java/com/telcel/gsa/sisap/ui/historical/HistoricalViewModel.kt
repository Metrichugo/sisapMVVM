package com.telcel.gsa.sisap.ui.historical

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.ui.network.HistoricalFolio
import com.telcel.gsa.sisap.ui.network.HistoricalFolioRequest
import com.telcel.gsa.sisap.ui.network.SisapApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.ArrayList

class HistoricalViewModel(private val idFolio : Int) : ViewModel() {

    private val _historicalFolio = MutableLiveData<HistoricalFolio>()
    val historicalFolio : LiveData<HistoricalFolio>
    get() = _historicalFolio

    init {
        getHistoricalData()
    }

    fun getHistoricalData(){
        viewModelScope.launch {
            try {
                _historicalFolio.value = SisapApi.retrofitService.getHistoricalFolio(HistoricalFolioRequest(idFolio.toString()))
                Log.d("Historical",_historicalFolio.value.toString())
            }catch (e : Exception){
                _historicalFolio.value = HistoricalFolio(ArrayList())
                e.message?.let { Log.d("Historical", it) }
                Log.d("Historical","Error al recuperar el historico del folio")
            }
        }
    }
}