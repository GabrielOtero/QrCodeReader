package com.otero.qrcodereader.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.otero.qrcodereader.model.QrCodeInfoModel

@Dao
interface NoteDao{
    @Insert
    fun insertNotes(vararg notes: QrCodeInfoModel?) : Array<Long>

    @Query("SELECT * FROM qrCodesInfo ORDER BY id DESC")
    fun getNotes() : LiveData<List<QrCodeInfoModel>>
}