package com.telcel.gsa.sisap.ui.description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.FragmentDescriptionBinding
import com.telcel.gsa.sisap.parseComplexity
import com.telcel.gsa.sisap.parsePriority
import com.telcel.gsa.sisap.ui.network.FolioDetail

class DescriptionFragment : Fragment() {

    lateinit var descriptionViewModel: DescriptionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDescriptionBinding.inflate(inflater)
        val folioDetail = arguments?.getParcelable<FolioDetail>("detail_folio_parcel")!!
        val descriptionViewModelFactory = DescriptionViewModelFactory(folioDetail)
        descriptionViewModel = ViewModelProvider(this,descriptionViewModelFactory).get(DescriptionViewModel::class.java)
        binding.viewModel = descriptionViewModel

        return binding.root
    }

}