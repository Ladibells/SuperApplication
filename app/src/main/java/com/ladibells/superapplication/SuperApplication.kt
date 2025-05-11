package com.ladibells.superapplication

import android.app.Application
import com.ladibells.utilities.logging.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SuperApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(message = "Application is launched")
    }

    companion object {
        const val TAG = "SuperApplication"
    }

}