package com.telcel.gsa.sisap.ui.folio

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.LoadingStatus
import com.telcel.gsa.sisap.database.getDatabase
import com.telcel.gsa.sisap.repository.FoliosRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class FolioViewModel(private val idEmployee: String, application: Application) : ViewModel() {

    private val _status = MutableLiveData<LoadingStatus>()
    val status : LiveData<LoadingStatus>
    get() = _status

    private val database = getDatabase(application)
    private val foliosRepository = FoliosRepository(database)

    private val _navigateToFolioDetail = MutableLiveData<Int?>()
    val navigateToFolioDetail: LiveData<Int?>
    get() = _navigateToFolioDetail

    private val _filterStatus = MutableLiveData<List<String>>()
    val filterStatus : LiveData<List<String>>
    get() = _filterStatus

    private val _filters = MutableLiveData<ArrayList<String>>()
    val filters : LiveData<ArrayList<String>>
    get() = _filters

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing : LiveData<Boolean>
    get() = _isRefreshing

    init {
        viewModelScope.launch {
            foliosRepository.refreshFolios()
            _isRefreshing.value = false
        }
    }

    val folios = foliosRepository.folios

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

    fun refreshData(){
        viewModelScope.launch {
            foliosRepository.refreshFolios()
            _isRefreshing.value = false
        }
    }

    private fun getFolios() {
        viewModelScope.launch {
            _status.value = LoadingStatus.LOADING
            try {
                //_folios.value = SisapApi.retrofitService.getFolios(FoliosRequest(idEmployee))
                getStatusFilters()
                _status.value = LoadingStatus.DONE
            }catch (e:Exception){
                _status.value = LoadingStatus.ERROR
                //_folios.value = FoliosList(ArrayList())
            }
            _isRefreshing.value = false
        }
    }

    fun getStatusFilters(){
        val statusArray = ArrayList<String>()
        val distinctFolios = folios.value?.distinctBy {
            folio -> folio.status
          }
        distinctFolios?.forEach {
            statusArray.add(it.status)
        }
         _filterStatus.value = statusArray
    }
}