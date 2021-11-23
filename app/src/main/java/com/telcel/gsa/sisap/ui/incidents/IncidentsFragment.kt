package com.telcel.gsa.sisap.ui.incidents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.telcel.gsa.sisap.databinding.FragmentIncidentsBinding

class IncidentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentIncidentsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val incidentViewModelFactory = IncidentViewModelFactory("15278")
        binding.viewModel = ViewModelProvider(this,incidentViewModelFactory).get(IncidentsViewModel::class.java)
        binding.incidentsList.adapter = IncidentsAdapter()
        return binding.root
    }
}