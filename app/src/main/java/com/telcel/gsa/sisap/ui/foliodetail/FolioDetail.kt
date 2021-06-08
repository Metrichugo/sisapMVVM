package com.telcel.gsa.sisap.ui.foliodetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.ActivityFolioDetailBinding
import com.telcel.gsa.sisap.ui.classification.ClassificationActivity

class FolioDetail : FragmentActivity() {

    lateinit var folioDetailViewModel: FolioDetailViewModel
    lateinit var idFolio : String
    lateinit var idEmployee : String
    lateinit var pagerAdapter: FolioDetailPagerAdapter
    val classificationRequestCode : Int = 1

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
            pagerAdapter = FolioDetailPagerAdapter(this,it,idEmployee)
            binding.pager.adapter = pagerAdapter
            TabLayoutMediator(binding.tabLayout,binding.pager){ tab,position ->
                when(position){
                    0 -> tab.text = getString(R.string.detail_tab_title)
                    1 -> tab.text = getString(R.string.work_team_tab_title)
                    2 -> tab.text = getString(R.string.committee_members_tab_tilte)
                    3 -> tab.text = getString(R.string.historical_tab_title)
                    else-> tab.text = getString(R.string.documents_tab_title)
                }
            }.attach()
            binding.actionButton.setOnClickListener { view: View ->
                it?.flowControl?.let { flowControl ->
                    when(flowControl.idFlowControl){
                        1-> {
                            val classificationActivity = Intent(applicationContext,ClassificationActivity::class.java)
                            classificationActivity.putExtra(getString(R.string.id_employee_extra),idEmployee)
                            classificationActivity.putExtra(getString(R.string.id_folio_extra),idFolio)
                            startActivityForResult(classificationActivity,classificationRequestCode)
                        }
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == classificationRequestCode){
            if(resultCode == RESULT_OK){
                finish()
            }
        }
    }
}