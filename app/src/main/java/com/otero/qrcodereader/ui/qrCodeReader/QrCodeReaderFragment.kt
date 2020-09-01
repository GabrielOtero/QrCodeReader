package com.otero.qrcodereader.ui.qrCodeReader

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.material.snackbar.Snackbar
import com.otero.qrcodereader.R
import com.otero.qrcodereader.model.QrCodeInfoModel
import com.otero.qrcodereader.model.QrCodeInfoUIModel
import com.otero.qrcodereader.repository.QrCodeInfoRepository
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever
import java.util.*


class QrCodeReaderFragment : Fragment(), BarcodeRetriever {

    private lateinit var qrCodeReaderViewModel: QrCodeReaderViewModel
    private lateinit var barcodeCapture: BarcodeCapture

    private lateinit var noteRepository: QrCodeInfoRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qrCodeReaderViewModel =
            ViewModelProviders.of(this).get(QrCodeReaderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_qr_code_reader, container, false)
        barcodeCapture = childFragmentManager.fragments[0] as BarcodeCapture
        barcodeCapture.setRetrieval(this@QrCodeReaderFragment)

        noteRepository = QrCodeInfoRepository(context!!)
        return root
    }

    override fun onRetrieved(barcode: Barcode?) {
        barcode?.let {
            barcodeCapture.pause()
            Log.d("QrCodeReaderFragment", "Barcode read: " + barcode.displayValue)
            QrCodeConfirmationDialog(
                QrCodeInfoUIModel(barcode.displayValue),
                onConfirm = {
                    noteRepository.insertNoteTask(QrCodeInfoModel(
                        value = barcode.displayValue,
                        timeStamp = System.currentTimeMillis()
                    ))
                    barcodeCapture.resume()
                    Snackbar.make(
                        view!!,
                        getString(R.string.qr_code_confirmation_dialog_success_message),
                        Snackbar.LENGTH_SHORT
                    ).show()
                },
                onCancel = { barcodeCapture.resume() }
            ).show(parentFragmentManager)
        }
    }

    override fun onRetrievedMultiple(
        closetToClick: Barcode?,
        barcode: MutableList<BarcodeGraphic>?
    ) {
    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>?) {
    }

    override fun onRetrievedFailed(reason: String?) {
    }

    override fun onPermissionRequestDenied() {
    }
}