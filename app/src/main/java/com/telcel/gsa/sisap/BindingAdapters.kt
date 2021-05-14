package com.telcel.gsa.sisap

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.telcel.gsa.sisap.ui.description.DescriptionViewModel
import com.telcel.gsa.sisap.ui.documents.FolioDocumentsAdapter
import com.telcel.gsa.sisap.ui.incidents.IncidentsAdapter
import com.telcel.gsa.sisap.ui.folio.FoliosAdapter
import com.telcel.gsa.sisap.ui.network.*
import com.telcel.gsa.sisap.ui.workteam.WorkTeamAdapter

@BindingAdapter("incidentsListData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<Incidence>?){
    data?.let {
        val adapter = recyclerView.adapter as IncidentsAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("foliosListData")
fun bindRecyclerViewFolios(recyclerView: RecyclerView, data : List<Folio>?){
    data?.let {
        val adapter = recyclerView.adapter as FoliosAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("workTeamListData")
fun bindRecyclerViewWorkTeam(recyclerView: RecyclerView, data : List<Employee>?){
    data?.let {
        val adapter = recyclerView.adapter as WorkTeamAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("documentsFolioListData")
fun bindRecyclerViewDocumentsFolio(recyclerView: RecyclerView,data: List<Document>?){
    data?.let{
        val adapter = recyclerView.adapter as FolioDocumentsAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("complexityString")
fun MaterialTextView.bindTextViewComplexity(folioDetail: FolioDetail){
    text = parseComplexity(folioDetail)
}

@BindingAdapter("priorityString")
fun MaterialTextView.bindTextViewPriority(folioDetail: FolioDetail){
    text = parsePriority(folioDetail)
}

@BindingAdapter("uploadedDate")
fun MaterialTextView.bindTextViewUploadedDate(uploadDate : String){
    text = this.context.getString(R.string.uploaded_date,uploadDate)
}
