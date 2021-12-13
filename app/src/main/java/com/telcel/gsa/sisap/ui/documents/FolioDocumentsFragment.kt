package com.telcel.gsa.sisap.ui.documents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.FragmentFolioDocumentsBinding

class FolioDocumentsFragment : Fragment(){

    lateinit var folioDocumentsViewModel: FolioDocumentsViewModel
    lateinit var folioDocumentsAdapter: FolioDocumentsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFolioDocumentsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val idFolio = arguments?.getInt(getString(R.string.id_folio_extra))
        val idEmployee = arguments?.getString(getString(R.string.id_employee_extra))
        val folioDocumentsViewModelFactory = FolioDocumentsViewModelFactory((idFolio!!).toString(),idEmployee!!)
        folioDocumentsViewModel = ViewModelProvider(this,folioDocumentsViewModelFactory).get(FolioDocumentsViewModel::class.java)
        binding.viewModel = folioDocumentsViewModel
        folioDocumentsAdapter = FolioDocumentsAdapter(FolioDocumentsAdapter.DocumentListener{
            _ -> folioDocumentsViewModel.onDocumentClicked(idFolio)
        })
        val mDividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.documentsFolioList.addItemDecoration(mDividerItemDecoration)
        binding.documentsFolioList.adapter = folioDocumentsAdapter
        return binding.root
    }
}