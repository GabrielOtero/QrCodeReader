package com.otero.qrcodereader.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.otero.qrcodereader.async.InsertAsyncTask
import com.otero.qrcodereader.model.QrCodeInfoModel
import com.otero.qrcodereader.persistence.QrCodeInfoDatabase

class QrCodeInfoRepository(context : Context) {

    private var noteDatabase: QrCodeInfoDatabase = QrCodeInfoDatabase.getInstance(context)

    fun insertNoteTask(info: QrCodeInfoModel) {
        InsertAsyncTask(noteDatabase.noteDao).execute(info)
    }

    fun retrieveNotesTask(): LiveData<List<QrCodeInfoModel>> = noteDatabase.noteDao.getNotes()

    fun retrieveNotesTaskByDate(startDate: Long, endDate: Long) =
        noteDatabase.noteDao.getNotesByDate(startDate, endDate)
}