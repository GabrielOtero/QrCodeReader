package com.otero.qrcodereader.ui.readList

import android.app.DownloadManager
import android.content.Context.DOWNLOAD_SERVICE
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.otero.qrcodereader.R
import com.otero.qrcodereader.extensions.getExportTimestamp
import com.otero.qrcodereader.repository.QrCodeInfoRepository
import kotlinx.android.synthetic.main.fragment_read_list.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*


class ReadListFragment : Fragment(), View.OnClickListener {

    private lateinit var readListViewModel: ReadListViewModel
    private lateinit var qrCodeInfoRepository: QrCodeInfoRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        readListViewModel =
            ViewModelProviders.of(this).get(ReadListViewModel::class.java)
        qrCodeInfoRepository = QrCodeInfoRepository(context!!)
        retrieveQrCodes()
        return inflater.inflate(R.layout.fragment_read_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        btn_download.setOnClickListener(this)
    }

    private fun retrieveQrCodes() {
        qrCodeInfoRepository.retrieveNotesTask().observe(viewLifecycleOwner, Observer { qrCodeModels ->
            (read_list.adapter as ResultAdapter).qrCodeInfoModels = qrCodeModels
            read_list.adapter?.notifyDataSetChanged()
        })

    }

    private fun initRecyclerView() {
        val resultAdapter = ResultAdapter()
        val verticalSpacingItemDecorator = VerticalSpacingItemDecorator(32)

        read_list.adapter = resultAdapter
        read_list.addItemDecoration(verticalSpacingItemDecorator)
        read_list.layoutManager =
            LinearLayoutManager(activity?.baseContext, LinearLayoutManager.VERTICAL, false)
    }

    override fun onClick(v: View?) {
        when(v){
            btn_download -> {
                ExportConfirmationDialog(
                    onConfirm = { startDate, endDate ->
                        qrCodeInfoRepository.retrieveNotesTaskByDate(startDate, endDate)
                            .observe(viewLifecycleOwner, Observer { qrCodeModels ->
                                when {
                                    startDate > endDate -> showMessage(getString(R.string.export_list_date_error_message))
                                    qrCodeModels.isEmpty() -> showMessage(getString(R.string.export_list_no_data_error_message))
                                    else -> {
                                        val fileName =
                                            "export-" + Date().getExportTimestamp() + ".csv"
                                        val content =
                                            qrCodeModels.map { it.value }.joinToString("\n")
                                        exportFile(content, fileName)
                                        showMessage(
                                            getString(
                                                R.string.export_list_confirmation_dialog_success_message,
                                                fileName
                                            )
                                        )
                                    }
                                }
                            })
                    },
                    onCancel = {

                    }
                ).show(parentFragmentManager)
            }
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            view!!,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun exportFile(content: String, fileName: String) {
        val absolutePath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .absolutePath + File.separator + fileName

        val file = File(absolutePath)
        val stream: OutputStream =
            FileOutputStream(file)

        stream.write(content.toByteArray())
        stream.flush()
        stream.close()

        val downloadManager =
            context!!.getSystemService(DOWNLOAD_SERVICE) as DownloadManager

        downloadManager.addCompletedDownload(
            file.name,
            file.name,
            true,
            "text/plain",
            file.absolutePath,
            file.length(),
            true
        )
    }
}