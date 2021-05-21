package com.telcel.gsa.sisap.ui.committee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.databinding.CommitteeMemberItemBinding
import com.telcel.gsa.sisap.databinding.WorkTeamItemBinding
import com.telcel.gsa.sisap.ui.network.CommitteeMember
import com.telcel.gsa.sisap.ui.network.Employee

class CommitteeAdapter : ListAdapter<CommitteeMember, CommitteeAdapter.CommitteeViewHolder>(DiffCallback) {

    class CommitteeViewHolder(private var binding : CommitteeMemberItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(member: CommitteeMember){
            binding.member = member
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CommitteeMember>(){
        override fun areItemsTheSame(oldItem: CommitteeMember, newItem: CommitteeMember): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CommitteeMember, newItem: CommitteeMember): Boolean {
            return oldItem.idEmployee == newItem.idEmployee
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitteeViewHolder {
        return CommitteeViewHolder(CommitteeMemberItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CommitteeViewHolder, position: Int) {
        val committeeMember = getItem(position)
        holder.bind(committeeMember)
    }
}