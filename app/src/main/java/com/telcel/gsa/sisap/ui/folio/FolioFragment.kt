package com.telcel.gsa.sisap.ui.folio

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.FragmentFolioBinding
import com.telcel.gsa.sisap.ui.foliodetail.FolioDetail

class FolioFragment : Fragment() {

    lateinit var folioViewModel: FolioViewModel
    lateinit var adapter: FoliosAdapter
    val idEmployee : String = "18858"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFolioBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val folioViewModelFactory = FolioViewModelFactory(idEmployee)
        folioViewModel = ViewModelProvider(this,folioViewModelFactory).get(FolioViewModel::class.java)
        binding.viewModel = folioViewModel
        adapter = FoliosAdapter(FoliosAdapter.FolioListener {
            idFolio -> folioViewModel.onFolioClicked(idFolio)
        })
        binding.incidentsList.adapter = adapter
        folioViewModel.navigateToFolioDetail.observe(viewLifecycleOwner, Observer {  folio ->
            folio?.let {
                doNavigationIntent(folio)
                folioViewModel.onFolioNavigated()
        }})

        folioViewModel.filterStatus.observe(viewLifecycleOwner, Observer {
            createChipsFilters(binding,it)
        })

        return binding.root
    }

    private fun doNavigationIntent(idFolio : Int ){
        val detailFragmentIntent = Intent(context,FolioDetail::class.java)
        detailFragmentIntent.putExtra(getString(R.string.id_folio_extra),idFolio)
        detailFragmentIntent.putExtra(getString(R.string.id_employee_extra),idEmployee)
        startActivity(detailFragmentIntent)
    }

    private fun createChipsFilters(binding: FragmentFolioBinding, statusFilterList:List<String>?){
        statusFilterList?.forEach { it ->
            val chip = Chip(context)
            chip.isCheckable = true
            chip.text = it
            chip.chipBackgroundColor = ColorStateList.valueOf(requireContext().getColor(R.color.color_primary))
            chip.setTextColor(requireContext().getColor(R.color.color_on_primary))
            chip.checkedIconTint = ColorStateList.valueOf(requireContext().getColor(R.color.color_secondary_variant))
            chip.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
                folioViewModel.onChipClicked(chip.text.toString())
                folioViewModel.filters.observe(viewLifecycleOwner, Observer { status->
                    adapter.applyFilters(status,folioViewModel.folios.value)
                })
                if(b){
                    chip.setTextColor(requireContext().getColor(R.color.color_secondary_variant))
                }else{
                    chip.setTextColor(requireContext().getColor(R.color.color_on_primary))
                }
            }
            binding.stateChipGroupFilter.addView(chip)
        }
    }


}