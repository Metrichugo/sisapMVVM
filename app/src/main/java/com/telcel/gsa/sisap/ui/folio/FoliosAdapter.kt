package com.telcel.gsa.sisap.ui.folio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.databinding.FolioItemBinding
import com.telcel.gsa.sisap.domain.Folio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoliosAdapter(val clickListener: FolioListener): ListAdapter<Folio,FoliosAdapter.FoliosListViewHolder>(DiffCallback) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

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

    fun applyFilters(filters: ArrayList<String>, value: List<Folio>?){
        adapterScope.launch {
            val filteredFolios = value?.filter{
                filters.contains(it.status)
            }
            withContext(Dispatchers.Main){
                if(filteredFolios.isNullOrEmpty()){
                    submitList(value)
                }else{
                    submitList(filteredFolios)
                }
            }
        }
    }

    class FolioListener(val clickListener: (idFolio: Int) -> Unit){
        fun onClick(folio: Folio) = clickListener(folio.idFolio)
    }

}