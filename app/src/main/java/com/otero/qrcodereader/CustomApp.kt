package com.otero.qrcodereader

import android.app.Application
import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.exitProcess

class CustomApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}