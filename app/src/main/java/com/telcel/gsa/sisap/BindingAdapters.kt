package com.telcel.gsa.sisap

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.ui.incidents.IncidentsAdapter
import com.telcel.gsa.sisap.ui.network.Incidence

@BindingAdapter("incidentsListData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<Incidence>){
    val adapter = recyclerView.adapter as IncidentsAdapter
    adapter.submitList(data)
}