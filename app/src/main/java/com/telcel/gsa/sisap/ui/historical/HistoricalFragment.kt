package com.telcel.gsa.sisap.ui.historical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.FragmentHistoricalBinding

class HistoricalFragment : Fragment() {

    lateinit var historicalViewModel: HistoricalViewModel
    lateinit var historicalAdapter: HistoricalAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHistoricalBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val idFolio = arguments?.getInt(getString(R.string.id_folio_extra))
        val historicalViewModelFactory = HistoricalViewModelFactory(idFolio!!)
        historicalViewModel = ViewModelProvider(this,historicalViewModelFactory).get(
            HistoricalViewModel::class.java)
        historicalAdapter = HistoricalAdapter()
        binding.viewModel = historicalViewModel
        val mDividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.committeeMembersList.addItemDecoration(mDividerItemDecoration)
        binding.committeeMembersList.adapter = historicalAdapter
        return binding.root
    }
}