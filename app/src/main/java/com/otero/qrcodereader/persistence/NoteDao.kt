package com.otero.qrcodereader.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.otero.qrcodereader.model.QrCodeInfoModel

const val THIRTY_ONE_DAYS = 2678400000L

@Dao
interface NoteDao {
    @Insert
    fun insertNotes(vararg notes: QrCodeInfoModel?): Array<Long>

    @Query("SELECT * FROM qrCodesInfo ORDER BY id DESC")
    fun getNotes(): LiveData<List<QrCodeInfoModel>>

    @Query("DELETE FROM qrCodesInfo WHERE timeStamp < :date")
    fun deleteNotesOlderThan(date: Long = (System.currentTimeMillis() - THIRTY_ONE_DAYS))

    @Query("SELECT * FROM qrCodesInfo WHERE timeStamp >= :startDate AND timeStamp <= :endDate")
    fun getNotesByDate(startDate: Long, endDate: Long): LiveData<List<QrCodeInfoModel>>
}