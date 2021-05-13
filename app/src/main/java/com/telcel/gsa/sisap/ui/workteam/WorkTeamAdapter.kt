package com.telcel.gsa.sisap.ui.workteam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telcel.gsa.sisap.databinding.WorkTeamItemBinding
import com.telcel.gsa.sisap.ui.network.Employee

class WorkTeamAdapter : ListAdapter<Employee,WorkTeamAdapter.WorkTeamViewHolder>(DiffCallback) {

    class WorkTeamViewHolder(private var binding : WorkTeamItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(employee: Employee){
            binding.employee = employee
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Employee>(){
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.idEmployee == newItem.idEmployee
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkTeamViewHolder {
        return WorkTeamViewHolder(WorkTeamItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: WorkTeamViewHolder, position: Int) {
        val employee = getItem(position)
        holder.bind(employee)
    }
}