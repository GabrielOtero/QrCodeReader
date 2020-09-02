package com.otero.qrcodereader.async

import android.os.AsyncTask
import com.otero.qrcodereader.model.QrCodeInfoModel
import com.otero.qrcodereader.persistence.NoteDao

class DeleteAsyncTask(private var noteDao: NoteDao) : AsyncTask<QrCodeInfoModel, Void, Void>() {
    override fun doInBackground(vararg notes: QrCodeInfoModel?): Void? {
        noteDao.deleteNotesOlderThan()
        return null
    }
}