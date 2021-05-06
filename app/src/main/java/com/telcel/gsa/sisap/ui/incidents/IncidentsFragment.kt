package com.telcel.gsa.sisap.ui.incidents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.FragmentIncidentsBinding

class IncidentsFragment : Fragment() {

    private lateinit var incidentsViewModel: IncidentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentIncidentsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = IncidentsViewModel()
        binding.incidentsList.adapter = IncidentsAdapter()
        return binding.root
    }
}