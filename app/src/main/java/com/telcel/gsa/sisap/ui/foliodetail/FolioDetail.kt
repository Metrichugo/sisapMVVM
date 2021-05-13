package com.telcel.gsa.sisap.ui.foliodetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.ActivityFolioDetailBinding

class FolioDetail : FragmentActivity() {

    lateinit var folioDetailViewModel: FolioDetailViewModel
    lateinit var idFolio : String
    lateinit var idEmployee : String
    lateinit var pagerAdapter: FolioDetailPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        bundle?.let {
            idFolio = bundle.getInt(getString(R.string.id_folio_extra),0).toString()
            idEmployee = bundle.getString(getString(R.string.id_employee_extra),"")
        }
        val binding = DataBindingUtil.setContentView<ActivityFolioDetailBinding>(this, R.layout.activity_folio_detail)
        binding.lifecycleOwner = this
        val folioDetailViewModelFactory = FolioDetailViewModelFactory(idFolio,idEmployee)
        folioDetailViewModel = ViewModelProvider(this,folioDetailViewModelFactory).get(FolioDetailViewModel::class.java)
        binding.viewModel = folioDetailViewModel
        folioDetailViewModel.detailFolio.observe(this, Observer {
            pagerAdapter = FolioDetailPagerAdapter(this,it)
            binding.pager.adapter = pagerAdapter
            TabLayoutMediator(binding.tabLayout,binding.pager){ tab,position ->
                when(position){
                    0 -> tab.text = "DETALLE"
                    else -> tab.text = "DETALLE"
                }
            }.attach()
        })
    }
}