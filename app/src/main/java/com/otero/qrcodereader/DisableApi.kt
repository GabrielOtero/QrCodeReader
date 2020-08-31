package com.otero.qrcodereader

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.http.GET

interface DisableApi {

    @GET("qrCodeReader")
    fun getDisabled(): Call<DisabledDto>

}

@Parcelize
data class DisabledDto(
    @SerializedName("disable") var disable: Boolean
) : Parcelable
