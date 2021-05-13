package com.telcel.gsa.sisap

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.ui.incidents.IncidentsAdapter
import com.telcel.gsa.sisap.ui.network.Folio
import com.telcel.gsa.sisap.ui.network.Incidence
import com.telcel.gsa.sisap.ui.folio.FoliosAdapter
import com.telcel.gsa.sisap.ui.network.Employee
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