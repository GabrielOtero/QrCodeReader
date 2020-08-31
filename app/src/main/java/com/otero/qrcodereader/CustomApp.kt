package com.otero.qrcodereader

import android.app.Application
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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://yardman-qa.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        val api: DisableApi = retrofit.create(DisableApi::class.java)

        val call: Call<DisabledDto> = api.getDisabled()

        call.enqueue(object : Callback<DisabledDto> {
            override fun onFailure(call: Call<DisabledDto>, t: Throwable) {
                Log.d("CustomApp", "FAIL")
            }

            override fun onResponse(call: Call<DisabledDto>, response: Response<DisabledDto>) {
                if (response.body()!!.disable) {
                    Log.d("CustomApp", "Disabled")
                    exitProcess(0)
                }
                Log.d("CustomApp", "Enabled")
            }
        })


    }
}