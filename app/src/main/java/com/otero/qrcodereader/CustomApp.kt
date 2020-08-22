package com.otero.qrcodereader

import android.app.Application
import kotlin.system.exitProcess

class CustomApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if(false){
            exitProcess(0)
        }
    }
}