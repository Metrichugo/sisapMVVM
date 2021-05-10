package com.telcel.gsa.sisap

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.ui.incidents.IncidentsAdapter
import com.telcel.gsa.sisap.ui.network.Folio
import com.telcel.gsa.sisap.ui.network.Incidence
import com.telcel.gsa.sisap.ui.solicitudes.FoliosAdapter

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