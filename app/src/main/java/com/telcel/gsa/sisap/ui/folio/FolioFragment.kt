package com.telcel.gsa.sisap.ui.folio

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.activity.result.contract.ActivityResultContracts
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
    val idEmployee : String = "15278"
    val detailFolioRequestCode : Int = 1
    val launchFolioDetail = registerForActivityResult( ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentFolioBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val folioViewModelFactory = FolioViewModelFactory(idEmployee,requireActivity().application)
        folioViewModel = ViewModelProvider(this,folioViewModelFactory).get(FolioViewModel::class.java)
        binding.viewModel = folioViewModel
        adapter = FoliosAdapter(FoliosAdapter.FolioListener {
            idFolio -> folioViewModel.onFolioClicked(idFolio)
        })
        binding.incidentsList.adapter = adapter
        folioViewModel.navigateToFolioDetail.observe(viewLifecycleOwner, { folio ->
            folio?.let {
                doNavigationIntent(folio)
                folioViewModel.onFolioNavigated()
        }})

        folioViewModel.filterStatus.observe(viewLifecycleOwner, {
            createChipsFilters(binding,it)
        })

        folioViewModel.folios.observe(viewLifecycleOwner, {
            folioViewModel.getStatusFilters()
        })

        return binding.root
    }

    private fun doNavigationIntent(idFolio : Int ){
        val detailFragmentIntent = Intent(context,FolioDetail::class.java)
        detailFragmentIntent.putExtra(getString(R.string.id_folio_extra),idFolio)
        detailFragmentIntent.putExtra(getString(R.string.id_employee_extra),idEmployee)
        launchFolioDetail.launch(detailFragmentIntent)
    }

    private fun createChipsFilters(binding: FragmentFolioBinding, statusFilterList:List<String>?){
        binding.stateChipGroupFilter.removeAllViews()
        statusFilterList?.forEach { it ->
            val chip = Chip(context)
            chip.isCheckable = true
            chip.text = it
            chip.chipBackgroundColor = ColorStateList.valueOf(requireContext().getColor(R.color.color_primary))
            chip.setTextColor(requireContext().getColor(R.color.color_on_primary))
            chip.checkedIconTint = ColorStateList.valueOf(requireContext().getColor(R.color.color_secondary_variant))
            chip.setOnCheckedChangeListener{ _ : CompoundButton, b: Boolean ->
                folioViewModel.onChipClicked(chip.text.toString())
                folioViewModel.filters.observe(viewLifecycleOwner, { status->
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

    /**override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            detailFolioRequestCode -> {
                //TODO: VOLVER A CARGAR LA INFORMACIÓN
            }
        }
    }**/

}