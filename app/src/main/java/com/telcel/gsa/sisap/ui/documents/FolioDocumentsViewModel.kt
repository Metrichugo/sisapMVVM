package com.telcel.gsa.sisap.ui.documents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcel.gsa.sisap.ui.network.Document
import com.telcel.gsa.sisap.ui.network.DocumentList
import com.telcel.gsa.sisap.ui.network.DocumentsFolioRequest
import com.telcel.gsa.sisap.ui.network.SisapApi
import kotlinx.coroutines.launch
import java.lang.Exception

class FolioDocumentsViewModel(private val idFolio: String, private val idEmployee: String) :
    ViewModel() {

    private val _documentsList = MutableLiveData<DocumentList>()
    val documentsList: LiveData<DocumentList>
        get() = _documentsList

    private val _downloadDocument = MutableLiveData<Document?>()
    val downloadDocument: LiveData<Document?>
        get() = _downloadDocument

    init {
        getDocumentsFolio()
    }

    fun onDocumentClicked(document: Document) {
        _downloadDocument.value = document
    }

    fun onDocumentDownloaded() {
        _downloadDocument.value = null
    }

    private fun getDocumentsFolio() {
        viewModelScope.launch {
            try {
                _documentsList.value = SisapApi.retrofitService.getDocumentsFolio(DocumentsFolioRequest(idFolio, idEmployee))
            } catch (e: Exception) {
                _documentsList.value = DocumentList(ArrayList())
            }
        }
    }

}