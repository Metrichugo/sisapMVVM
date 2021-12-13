package com.telcel.gsa.sisap.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.telcel.gsa.sisap.database.SisapDatabase
import com.telcel.gsa.sisap.database.asDomainModel
import com.telcel.gsa.sisap.domain.Folio
import com.telcel.gsa.sisap.ui.network.FoliosRequest
import com.telcel.gsa.sisap.ui.network.SisapApi
import com.telcel.gsa.sisap.ui.network.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoliosRepository(private val database: SisapDatabase) {

    val folios : LiveData<List<Folio>> = Transformations.map(database.folioDao.getFolios()){
        it.asDomainModel()
    }

    suspend fun refreshFolios(){
        withContext(Dispatchers.IO){
            try {
                val userFolios = SisapApi.retrofitService.getFolios(FoliosRequest("15278"))
                database.folioDao.insertAll(*userFolios.asDataBaseModel())
            }catch(e : Exception){
                e.message?.let { Log.d("FoliosRepository", it) }
            }
        }
    }

}