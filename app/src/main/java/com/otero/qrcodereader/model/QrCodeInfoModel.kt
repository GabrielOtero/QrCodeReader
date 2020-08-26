package com.otero.qrcodereader.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "qrCodesInfo")
data class QrCodeInfoModel(
    @ColumnInfo(name = "value") var value: String? = "",
    @ColumnInfo(name = "timeStamp") var timeStamp: String? = ""
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}