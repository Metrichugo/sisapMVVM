package com.telcel.gsa.sisap.ui.foliodetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.ActivityFolioDetailBinding

class FolioDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFolioDetailBinding>(this, R.layout.activity_folio_detail)
        binding.lifecycleOwner = this
    }
}