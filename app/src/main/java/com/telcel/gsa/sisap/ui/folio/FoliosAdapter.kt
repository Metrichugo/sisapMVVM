package com.telcel.gsa.sisap.ui.folio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.databinding.FolioItemBinding
import com.telcel.gsa.sisap.ui.network.Folio

class FoliosAdapter(val clickListener: FolioListener): ListAdapter<Folio,FoliosAdapter.FoliosListViewHolder>(DiffCallback) {

    class FoliosListViewHolder(private var binding : FolioItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: FolioListener, folio: Folio){
            binding.folio = folio
            binding.clickListener = clickListener
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
        holder.bind(clickListener, folio)
    }

    class FolioListener(val clickListener: (idFolio: Int) -> Unit){
        fun onClick(folio: Folio) = clickListener(folio.idFolio)
    }

}