package com.telcel.gsa.sisap.ui.historical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.databinding.HistoricalItemBinding
import com.telcel.gsa.sisap.ui.network.ActionHistorical

class HistoricalAdapter : ListAdapter<ActionHistorical, HistoricalAdapter.HistoricalViewHolder>(DiffCallback){

    class HistoricalViewHolder(private var binding : HistoricalItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(actionHistorical: ActionHistorical){
            binding.actionHistorical = actionHistorical
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ActionHistorical>(){
        override fun areItemsTheSame(oldItem: ActionHistorical, newItem: ActionHistorical): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ActionHistorical, newItem: ActionHistorical): Boolean {
            return oldItem.idAction == newItem.idAction
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalViewHolder {
        return HistoricalViewHolder(HistoricalItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HistoricalViewHolder, position: Int) {
        val actionHistorical = getItem(position)
        holder.bind(actionHistorical)
    }
}