package com.telcel.gsa.sisap.ui.foliodetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.ui.committee.CommitteeFragment
import com.telcel.gsa.sisap.ui.description.DescriptionFragment
import com.telcel.gsa.sisap.ui.documents.FolioDocumentsFragment
import com.telcel.gsa.sisap.ui.network.FolioDetail
import com.telcel.gsa.sisap.ui.workteam.WorkTeamFragment

class FolioDetailPagerAdapter(private val fragmentActivity: FragmentActivity,private val folioDetail: FolioDetail?, private val idEmployee: String) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }
    override fun createFragment(position: Int): Fragment {
         when(position){
            0-> {
                val descriptionFragment = DescriptionFragment()
                descriptionFragment.arguments = Bundle().apply {
                    putParcelable(fragmentActivity.applicationContext.getString(R.string.detail_folio_parcel),folioDetail)
                }
                return descriptionFragment
            }
            1->{
                val workTeamFragment = WorkTeamFragment()
                workTeamFragment.arguments = Bundle().apply {
                    putParcelable(fragmentActivity.applicationContext.getString(R.string.detail_folio_parcel),folioDetail)
                }
                return workTeamFragment
            }
             2->{
                 val committeeFragment = CommitteeFragment()
                 committeeFragment.arguments = Bundle().apply {
                     putInt(fragmentActivity.applicationContext.getString(R.string.id_folio_extra),folioDetail!!.idFolio)
                 }
                 return committeeFragment
             }
             else->{
                 val folioDocumentsFragment = FolioDocumentsFragment()
                 folioDocumentsFragment.arguments = Bundle().apply {
                     putInt(fragmentActivity.applicationContext.getString(R.string.id_folio_extra),folioDetail!!.idFolio)
                     putString(fragmentActivity.applicationContext.getString(R.string.id_employee_extra),idEmployee)
                 }
                 return folioDocumentsFragment
             }
        }
    }
}