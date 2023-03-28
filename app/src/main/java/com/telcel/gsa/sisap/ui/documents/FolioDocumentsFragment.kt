package com.telcel.gsa.sisap.ui.documents

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.FragmentFolioDocumentsBinding
import com.telcel.gsa.sisap.ui.network.Document

class FolioDocumentsFragment : Fragment() {

    lateinit var folioDocumentsViewModel: FolioDocumentsViewModel
    lateinit var folioDocumentsAdapter: FolioDocumentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFolioDocumentsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val idFolio = arguments?.getInt(getString(R.string.id_folio_extra))
        val idEmployee = arguments?.getString(getString(R.string.id_employee_extra))
        val folioDocumentsViewModelFactory = FolioDocumentsViewModelFactory((idFolio!!).toString(), idEmployee!!)
        folioDocumentsViewModel = ViewModelProvider(this, folioDocumentsViewModelFactory)[FolioDocumentsViewModel::class.java]
        binding.viewModel = folioDocumentsViewModel
        folioDocumentsAdapter = FolioDocumentsAdapter(FolioDocumentsAdapter.DocumentListener {
            folioDocumentsViewModel.onDocumentClicked(it)
        })
        folioDocumentsViewModel.downloadDocument.observe(viewLifecycleOwner) { document ->
            document?.let {
                performDocumentDownload(it)
                folioDocumentsViewModel.onDocumentDownloaded()
            }
        }
        val mDividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.documentsFolioList.addItemDecoration(mDividerItemDecoration)
        binding.documentsFolioList.adapter = folioDocumentsAdapter
        return binding.root
    }

    private fun performDocumentDownload(document: Document) {
        val downloadManager =
            activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        with(DownloadManager.Request((Uri.parse("$SERVLET_URI${document.documentsInformation.fileDescription[0].serialName}")))) {
            this.setTitle("$document")
            this.addRequestHeader(MESSAGE_UUID, "356904090414635")
            this.setDescription(getString(R.string.downloading))
            this.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            this.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                document.documentsInformation.fileDescription[0].name
            )
            downloadManager.enqueue(this)
        }
    }

    companion object {
        const val MESSAGE_UUID = "messageUUID"
        const val SERVLET_URI =
            "https://oam.telcelinstitucional.com:5442/sisap-ws/GetFile?nombreArchivo="
    }
}