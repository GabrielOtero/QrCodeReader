package com.otero.qrcodereader.ui.readList

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.otero.qrcodereader.R
import java.text.SimpleDateFormat
import java.util.*


class ExportConfirmationDialog(
    val onConfirm: () -> Unit,
    val onCancel: () -> Unit
) : DialogFragment(), View.OnClickListener, DatePickerDialog.OnDateSetListener {


    private val startDateCalendar = Calendar.getInstance()
    private val endDateCalendar = Calendar.getInstance()

    private lateinit var startDateLabel : TextView
    private lateinit var endDateLabel : TextView

    private lateinit var startDatePicker : DatePickerDialog
    private lateinit var endDatePicker : DatePickerDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val view: View =
            inflater.inflate(R.layout.export_list_confirmation_dialog, null)

        builder.setView(view)
            .setMessage(getString(R.string.export_list_confirmation_dialog_title))
            .setPositiveButton(
                getString(R.string.export_list_confirmation_dialog_positive_button)
            ) { _, _ ->
                this.dismiss()
                onConfirm()
            }
            .setNegativeButton(
                getString(R.string.export_list_confirmation_dialog_negative_button)
            ) { _, _ ->
                this.dismiss()
                onCancel()
            }

        startDateLabel = view.findViewById(R.id.start_date)
        startDateLabel.setOnClickListener(this)

        endDateLabel = view.findViewById(R.id.end_date)
        endDateLabel.setOnClickListener(this)

        startDatePicker = DatePickerDialog(
            context!!, this, startDateCalendar[Calendar.YEAR], startDateCalendar[Calendar.MONTH],
            startDateCalendar[Calendar.DAY_OF_MONTH]
        )

        endDatePicker = DatePickerDialog(
            context!!, this, endDateCalendar[Calendar.YEAR], endDateCalendar[Calendar.MONTH],
            endDateCalendar[Calendar.DAY_OF_MONTH]
        )

        return builder.create()
    }

    fun show(fragmentManager: FragmentManager) {
        this.show(fragmentManager, ExportConfirmationDialog::class.qualifiedName)
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialogTheme
    }

    override fun onClick(v: View?) {
        when (v) {
            startDateLabel -> {
                startDatePicker.show()
            }
            endDateLabel -> {
                endDatePicker.show()
            }
        }
    }

    override fun onDateSet(dp: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        when(dp){
            startDatePicker.datePicker -> {
                startDateCalendar.set(Calendar.YEAR, year)
                startDateCalendar.set(Calendar.MONTH, monthOfYear)
                startDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel(startDateLabel, startDateCalendar)
            }

            endDatePicker.datePicker -> {
                endDateCalendar.set(Calendar.YEAR, year)
                endDateCalendar.set(Calendar.MONTH, monthOfYear)
                endDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel(endDateLabel, endDateCalendar)
            }
        }
    }

    private fun updateLabel(label : TextView, calendar : Calendar) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale("pt", "BR"))
        label.text = sdf.format(calendar.time)
    }
}