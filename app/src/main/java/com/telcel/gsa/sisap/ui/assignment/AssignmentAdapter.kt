package com.telcel.gsa.sisap.ui.assignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.databinding.TeamMemberItemBinding
import com.telcel.gsa.sisap.ui.network.TeamMember

class AssignmentAdapter(/*val clickListener: MemberTeamListener*/) : ListAdapter<TeamMember, AssignmentAdapter.TeamMemberViewHolder>(DiffCallback) {

    class TeamMemberViewHolder(private var binding : TeamMemberItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(/*clickListener: MemberTeamListener,*/ member: TeamMember){
            binding.member = member
            //binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TeamMember>(){
        override fun areItemsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem.idEmployee == newItem.idEmployee
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberViewHolder {
        return TeamMemberViewHolder(TeamMemberItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        val teamMember = getItem(position)
        holder.bind(/*clickListener,*/ teamMember)
    }

    class MemberTeamListener(val clickListener: (idEmployee: String) -> Unit){
        fun onClick(member: TeamMember) = clickListener(member.idEmployee)
    }
}