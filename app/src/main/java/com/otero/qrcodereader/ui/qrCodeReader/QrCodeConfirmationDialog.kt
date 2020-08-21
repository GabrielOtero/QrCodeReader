package com.otero.qrcodereader.ui.qrCodeReader

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.otero.qrcodereader.R


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
            .setMessage("Deseja confirmar os dados?")
            .setPositiveButton(
                "Confirmar"
            ) { _, _ ->
                this.dismiss()
                onConfirm()
            }
            .setNegativeButton(
                "Cancelar"
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