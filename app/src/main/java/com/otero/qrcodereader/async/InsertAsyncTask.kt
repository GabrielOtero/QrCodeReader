package com.otero.qrcodereader.async

import android.os.AsyncTask
import com.otero.qrcodereader.model.QrCodeInfoModel
import com.otero.qrcodereader.persistence.NoteDao

class InsertAsyncTask(private var noteDao: NoteDao) : AsyncTask<QrCodeInfoModel, Void, Void>() {
    override fun doInBackground(vararg notes: QrCodeInfoModel?): Void? {
        noteDao.insertNotes(*notes)
        return null
    }
}