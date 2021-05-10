package com.telcel.gsa.sisap.ui.solicitudes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.telcel.gsa.sisap.databinding.FragmentFolioBinding

class FolioFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFolioBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val folioViewModelFactory = FolioViewModelFactory("18858")
        binding.viewModel = ViewModelProvider(this,folioViewModelFactory).get(FolioViewModel::class.java)
        binding.incidentsList.adapter = FoliosAdapter()
        return binding.root
    }
}