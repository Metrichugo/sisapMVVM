package com.telcel.gsa.sisap.ui.solicitudes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.ui.network.FoliosList
import com.telcel.gsa.sisap.ui.network.FoliosRequest
import com.telcel.gsa.sisap.ui.network.SisapApi
import kotlinx.coroutines.launch
import java.lang.Exception

class FolioViewModel(private val idEmployee: String) : ViewModel() {

    private val _folios = MutableLiveData<FoliosList>()
    val folios : LiveData<FoliosList>
    get() = _folios

    init {
        getFolios()
    }

    private fun getFolios() {
        viewModelScope.launch {
            try {
                _folios.value = SisapApi.retrofitService.getFolios(FoliosRequest(idEmployee))
            }catch (e:Exception){
                _folios.value = FoliosList(ArrayList())
            }
        }
    }
}