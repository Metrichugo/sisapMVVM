package com.telcel.gsa.sisap.ui.incidents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.databinding.IncidenceItemBinding
import com.telcel.gsa.sisap.ui.network.Incidence

class IncidentsAdapter: ListAdapter<Incidence,IncidentsAdapter.IncidentsListViewHolder>(DiffCallback) {
    class IncidentsListViewHolder(private var binding : IncidenceItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(incidence: Incidence){
            binding.incidence = incidence
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Incidence>(){
        override fun areItemsTheSame(oldItem: Incidence, newItem: Incidence): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Incidence, newItem: Incidence): Boolean {
            return oldItem.idIncidence == newItem.idIncidence
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentsListViewHolder {
        return IncidentsListViewHolder(IncidenceItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: IncidentsListViewHolder, position: Int) {
        val incidence = getItem(position)
        holder.bind(incidence)
    }
}