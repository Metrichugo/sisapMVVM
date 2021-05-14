package com.telcel.gsa.sisap.ui.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.telcel.gsa.sisap.parseComplexity
import com.telcel.gsa.sisap.parsePriority
import com.telcel.gsa.sisap.ui.network.FolioDetail

class DescriptionViewModel(private val detail: FolioDetail) : ViewModel() {

    private val _folioDetail = MutableLiveData<FolioDetail>()
    val folioDetail : LiveData<FolioDetail>
    get() = _folioDetail

    init {
        _folioDetail.value = detail
    }

}