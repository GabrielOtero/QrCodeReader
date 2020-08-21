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
import com.otero.qrcodereader.R
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever


class QrCodeReaderFragment : Fragment(), BarcodeRetriever {

    private lateinit var qrCodeReaderViewModel: QrCodeReaderViewModel
    private lateinit var barcodeCapture: BarcodeCapture

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        qrCodeReaderViewModel =
            ViewModelProviders.of(this).get(QrCodeReaderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        barcodeCapture = childFragmentManager.fragments[0] as BarcodeCapture
        barcodeCapture.setRetrieval(this@QrCodeReaderFragment)
        return root
    }


    override fun onRetrieved(barcode: Barcode?) {
        barcode?.let {
            barcodeCapture.pause()
            Log.d("QrCodeReaderFragment", "Barcode read: " + barcode.displayValue)
            QrCodeConfirmationDialog(
                QrCodeInfoUIModel(barcode.displayValue),
                onConfirm = { barcodeCapture.resume() },
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