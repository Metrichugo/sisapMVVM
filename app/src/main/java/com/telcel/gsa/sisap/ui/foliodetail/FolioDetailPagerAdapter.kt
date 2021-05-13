package com.telcel.gsa.sisap.ui.foliodetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.ui.description.DescriptionFragment
import com.telcel.gsa.sisap.ui.network.FolioDetail

class FolioDetailPagerAdapter(private val fragmentActivity: FragmentActivity,private val folioDetail: FolioDetail?) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
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
            else->{
                val descriptionFragment = DescriptionFragment()
                descriptionFragment.arguments = Bundle().apply {
                    putParcelable("detail_folio_parcel",folioDetail)
                }
                return descriptionFragment
            }
        }
    }
}