package com.otero.qrcodereader.ui.qrCodeReader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otero.qrcodereader.repository.QrCodeInfoRepository

class QrCodeReaderViewModel() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}