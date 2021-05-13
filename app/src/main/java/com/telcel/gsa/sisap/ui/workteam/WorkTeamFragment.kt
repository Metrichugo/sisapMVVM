package com.telcel.gsa.sisap.ui.workteam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.telcel.gsa.sisap.databinding.FragmentWorkTeamBinding
import com.telcel.gsa.sisap.ui.network.FolioDetail

class WorkTeamFragment : Fragment() {

    lateinit var workTeamViewModel: WorkTeamViewModel
    lateinit var workTeamAdapter: WorkTeamAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWorkTeamBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val folioDetail = arguments?.getParcelable<FolioDetail>("detail_folio_parcel")!!
        val workTeamViewModelFactory = WorkTeamViewModelFactory(folioDetail.involvedEmployees)
        workTeamViewModel = ViewModelProvider(this,workTeamViewModelFactory).get(WorkTeamViewModel::class.java)
        workTeamAdapter = WorkTeamAdapter()
        binding.viewModel = workTeamViewModel
        var mDividerItemDecoration = DividerItemDecoration(context,LinearLayoutManager.VERTICAL)
        binding.workTeamList.addItemDecoration(mDividerItemDecoration)
        binding.workTeamList.adapter = workTeamAdapter
        return binding.root
    }
}