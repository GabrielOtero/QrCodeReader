package com.otero.qrcodereader.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.getCurrentTimestamp(): String {
    val format = SimpleDateFormat("dd/MM", Locale.getDefault())
    return format.format(this)
}