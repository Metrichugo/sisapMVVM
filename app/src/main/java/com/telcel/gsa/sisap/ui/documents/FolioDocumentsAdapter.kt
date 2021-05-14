package com.telcel.gsa.sisap.ui.documents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.databinding.DocumentItemBinding
import com.telcel.gsa.sisap.ui.network.Document

class FolioDocumentsAdapter(val clickListener: DocumentListener) : ListAdapter<Document,FolioDocumentsAdapter.FolioDocumentsViewHolder>(DiffCallBack){

    class FolioDocumentsViewHolder(val binding : DocumentItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: DocumentListener, document: Document){
            binding.document = document
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Document>(){
        override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem.idDocument == newItem.idDocument
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolioDocumentsViewHolder {
        return FolioDocumentsViewHolder(DocumentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FolioDocumentsViewHolder, position: Int) {
        val document = getItem(position)
        holder.bind(clickListener,document)
    }

    class DocumentListener(val clickListener: (idDocument: Int) -> Unit){
        fun onClick(document: Document) = clickListener(document.idDocument)
    }

}