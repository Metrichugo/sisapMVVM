package com.telcel.gsa.sisap.ui.classification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.ui.network.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class ClassificationViewModel(private val idEmployee: String, private val idFolio: String) : ViewModel() {

    private val _catalogs = MutableLiveData<Catalog>()
    val catalogs : LiveData<Catalog>
    get() = _catalogs

    private val _departmentBosses = MutableLiveData<BossesList>()
    val departmentBosses : LiveData<BossesList>
    get() = _departmentBosses

    private val _modules = MutableLiveData<ModulesList>()
    val modules : LiveData<ModulesList>
    get() = _modules

    private val _system = MutableLiveData<MSystem>()
    val system : LiveData<MSystem>
    get() = _system

    private val _complexity = MutableLiveData<Complexity>()
    val complexity : LiveData<Complexity>
        get() = _complexity

    private val _type = MutableLiveData<MType>()
    val type : LiveData<MType>
    get() = _type

    private val _priority = MutableLiveData<MPriority>()
    val priority : LiveData<MPriority>
    get() = _priority

    private val _boss = MutableLiveData<Boss>()
    val boss: LiveData<Boss>
    get() = _boss

    private val _module = MutableLiveData<Module>()
    val module : LiveData<Module>
    get() = _module

    private val _moduleSelected = MutableLiveData<Boolean>()
    val moduleSelected : LiveData<Boolean>
    get() = _moduleSelected

    private val _date = MutableLiveData<String?>()
    val date : LiveData<String?>
    get() = _date

    private val _checkdate = MutableLiveData<Boolean>()
    val checkdate : LiveData<Boolean>
    get() = _checkdate

    private val _enableClick = MutableLiveData<Boolean>()
    val enableClick : LiveData<Boolean>
    get() = _enableClick

    private val _classificationAction = MutableLiveData<GenericPostAppResponse?>()
    val classificationAction : LiveData<GenericPostAppResponse?>
    get() = _classificationAction

    init {
        getCatalogs()
        getBosses()
        _moduleSelected.value = true
        _enableClick.value = false
    }

    fun setSystemSelected(system: MSystem){
        _system.value = system
        checkEnable()
    }

    fun setComplexitySelected(complexity: Complexity){
        _complexity.value = complexity
        checkEnable()
    }

    fun setTypeSelected(type : MType){
        _type.value = type
        checkEnable()
    }

    fun setPrioritySelected(priority: MPriority){
        _priority.value = priority
        when(_priority.value!!.idPriority){
            5->
                _checkdate.value = true
            else -> {
                _date.value = null
                _checkdate.value = false
            }
        }
        checkEnable()
    }

    fun setBossSelected(boss: Boss){
        _boss.value = boss
        checkEnable()
    }

    fun setModuleSelected(module: Module){
        _module.value = module
        checkEnable()
    }

    fun setDatePicked(date : Long?){
        date?.let {
            _date.value = parseDate(it)
            checkEnable()
        }
    }

    fun doneModuleSelected(){
        _moduleSelected.value = true
    }

    private fun getCatalogs(){
        viewModelScope.launch {
            try{
                _catalogs.value = SisapApi.retrofitService.getCatalogs(CatalogRequest(idEmployee));
            }catch (e : Exception){
                _catalogs.value = Catalog(ArrayList(),ArrayList(),ArrayList(),ArrayList())
            }
        }
    }

    private fun getBosses(){
        viewModelScope.launch {
            try{
                _departmentBosses.value = SisapApi.retrofitService.getBosses(BossesListRequest(idEmployee))
            }catch (e : Exception){
                _departmentBosses.value = BossesList(ArrayList())
            }
        }
    }

    fun getModules(){
        viewModelScope.launch {
            try {
                _modules.value = SisapApi.retrofitService.getModules(ModulesRequest(_system.value?.idSystem.toString()))
                _moduleSelected.value = false
            }catch (e : Exception){
                _modules.value = ModulesList(ArrayList())
            }
        }
    }

    fun postClassification(){
        viewModelScope.launch {
            try{
               _classificationAction.value = SisapApi.retrofitService.postClassification(
                   ClassificationRequest(idFolio,
                       _complexity.value!!.idComplexity.toString(),
                       _system.value!!.idSystem.toString(),
                       _module.value!!.idModule,
                       _type.value!!.idFlow.toString(),
                       _priority.value!!.idPriority.toString(),
                       _date.value,
                       _boss.value!!.idEmployee,
                       LoggedUserTO(idEmployee),ArrayList()
                   ))
            }catch (e: Exception){
                _classificationAction.value = null
            }
        }
    }

    private fun checkEnable(){
        _enableClick.value = (system.value != null
                && module.value != null
                && complexity.value != null
                && type.value != null
                && priority.value != null
                && boss.value != null
                && ((_checkdate.value == true && date.value !=null) || _checkdate.value == false))
    }

    private fun parseDate(date : Long) : String{
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.time = Date(date)
        return twoDigits(calendar.get(Calendar.DAY_OF_MONTH)).plus("/").
        plus(twoDigits(calendar.get(Calendar.MONTH).plus(1))).plus("/").
        plus(calendar.get(Calendar.YEAR).toString()).
        plus(" 00:00:00")
    }

    private fun twoDigits(value: Int) : String{
        return when(value){
            in 1..9 -> "0".plus(value)
            else -> value.toString()
        }
    }
}