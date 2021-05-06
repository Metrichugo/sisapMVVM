package com.telcel.gsa.sisap.ui.incidents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.telcel.gsa.sisap.databinding.FragmentIncidentsBinding

class IncidentsFragment : Fragment() {

    private lateinit var incidentsViewModel: IncidentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentIncidentsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = IncidentsViewModel("18858")
        binding.incidentsList.adapter = IncidentsAdapter()
        return binding.root
    }
}