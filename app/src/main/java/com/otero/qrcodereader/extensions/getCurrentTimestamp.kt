package com.otero.qrcodereader.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.getCurrentTimestamp(): String {
    val format = SimpleDateFormat("dd/MM", Locale.getDefault())
    return format.format(this)
}

fun Date.getExportTimestamp(): String {
    val format = SimpleDateFormat("dd-MM-mmssSSS", Locale.getDefault())
    return format.format(this)
}

fun Long.toTimePtBr(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd/MM/YY hh:mm", Locale.getDefault())
    return format.format(date)
}