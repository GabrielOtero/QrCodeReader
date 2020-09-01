package com.otero.qrcodereader.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.otero.qrcodereader.model.QrCodeInfoModel

@Database(entities = [QrCodeInfoModel::class], version = 3)
abstract class QrCodeInfoDatabase : RoomDatabase(){
    abstract val noteDao: NoteDao

    companion object {

        private const val DATABASE_NAME = "qr_code_info_db"
        private var instance: QrCodeInfoDatabase? = null

        fun getInstance(context: Context): QrCodeInfoDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    QrCodeInfoDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return instance as QrCodeInfoDatabase
        }
    }
}