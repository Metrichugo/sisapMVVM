package com.telcel.gsa.sisap.ui.committee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.FragmentCommitteeBinding

class CommitteeFragment: Fragment() {

    lateinit var committeeViewModel: CommitteeViewModel
    lateinit var committeeAdapter: CommitteeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCommitteeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val idFolio = arguments?.getInt(getString(R.string.id_folio_extra))
        val committeeViewModelFactory = CommitteeViewModelFactory(idFolio!!)
        committeeViewModel = ViewModelProvider(this,committeeViewModelFactory).get(CommitteeViewModel::class.java)
        committeeAdapter = CommitteeAdapter()
        binding.viewModel = committeeViewModel
        var mDividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.committeeMembersList.addItemDecoration(mDividerItemDecoration)
        binding.committeeMembersList.adapter = committeeAdapter
        return binding.root
    }
}