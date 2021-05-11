package com.telcel.gsa.sisap.ui.solicitudes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.databinding.FolioItemBinding
import com.telcel.gsa.sisap.ui.network.Folio

class FoliosAdapter: ListAdapter<Folio,FoliosAdapter.FoliosListViewHolder>(DiffCallback) {

    class FoliosListViewHolder(private var binding : FolioItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(folio: Folio){
            binding.folio = folio
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Folio>(){
        override fun areItemsTheSame(oldItem: Folio, newItem: Folio): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Folio, newItem: Folio): Boolean {
            return oldItem.idFolio == newItem.idFolio
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoliosListViewHolder {
        return FoliosListViewHolder(FolioItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FoliosListViewHolder, position: Int) {
        val folio = getItem(position)
        holder.bind(folio)
    }
}