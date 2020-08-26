package com.otero.qrcodereader.ui.qrCodeReader

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.otero.qrcodereader.R
import com.otero.qrcodereader.model.QrCodeInfoUIModel


class QrCodeConfirmationDialog(
    private val qrCodeUIModel: QrCodeInfoUIModel,
    val onConfirm: () -> Unit,
    val onCancel: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val view: View =
            inflater.inflate(R.layout.fragment_qr_code_confirmation_dialog, null)

        val qrCodeInfo = view.findViewById<TextView>(R.id.qr_code_info)
        qrCodeInfo.text = qrCodeUIModel.value

        builder.setView(view)
            .setMessage(getString(R.string.qr_code_confirmation_dialog_title))
            .setPositiveButton(
                getString(R.string.qr_code_confirmation_dialog_positive_button)
            ) { _, _ ->
                this.dismiss()
                onConfirm()
            }
            .setNegativeButton(
                getString(R.string.qr_code_confirmation_dialog_negative_button)
            ) { _, _ ->
                this.dismiss()
                onCancel()
            }

        return builder.create()
    }

    fun show(fragmentManager: FragmentManager) {
        this.show(fragmentManager, QrCodeConfirmationDialog::class.qualifiedName)
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialogTheme
    }
}