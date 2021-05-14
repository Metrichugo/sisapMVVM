package com.telcel.gsa.sisap.ui.folio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.LoadingStatus
import com.telcel.gsa.sisap.ui.network.FoliosList
import com.telcel.gsa.sisap.ui.network.FoliosRequest
import com.telcel.gsa.sisap.ui.network.SisapApi
import kotlinx.coroutines.launch
import java.lang.Exception

class FolioViewModel(private val idEmployee: String) : ViewModel() {

    private val _status = MutableLiveData<LoadingStatus>()
    val status : LiveData<LoadingStatus>
    get() = _status

    private val _folios = MutableLiveData<FoliosList>()
    val folios : LiveData<FoliosList>
    get() = _folios

    private val _navigateToFolioDetail = MutableLiveData<Int?>()
    val navigateToFolioDetail: LiveData<Int?>
    get() = _navigateToFolioDetail

    private val _filterStatus = MutableLiveData<List<String>>()
    val filterStatus : LiveData<List<String>>
    get() = _filterStatus

    private val _filters = MutableLiveData<ArrayList<String>>()
    val filters : LiveData<ArrayList<String>>
    get() = _filters

    init {
        getFolios()
    }

    fun onFolioClicked(id: Int){
        _navigateToFolioDetail.value = id
    }

    fun onFolioNavigated(){
        _navigateToFolioDetail.value = null
    }

    fun onChipClicked( status : String){
        if(filters.value.isNullOrEmpty()){
            _filters.value = arrayListOf(status)
        }else{
            _filters.value?.let {
                if (it.contains(status)) it.remove(status) else _filters.value?.add(status)
            }
        }
    }



    private fun getFolios() {
        viewModelScope.launch {
            _status.value = LoadingStatus.LOADING
            try {
                _folios.value = SisapApi.retrofitService.getFolios(FoliosRequest(idEmployee))
                getStatusFilters()
                _status.value = LoadingStatus.DONE
            }catch (e:Exception){
                _status.value = LoadingStatus.ERROR
                _folios.value = FoliosList(ArrayList())
            }
        }
    }

    private fun getStatusFilters(){
        val statusArray = ArrayList<String>()
        var distinctFolios = _folios.value?.foliosList?.distinctBy {
            folio -> folio.status
          }
        distinctFolios?.forEach {
            statusArray.add(it.status)
        }
         _filterStatus.value = statusArray
    }
}