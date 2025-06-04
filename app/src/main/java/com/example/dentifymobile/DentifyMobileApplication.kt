package com.example.dentifymobile

import android.app.Application

class DentifyMobileApplication: Application() {
    companion object {
        lateinit var instance: DentifyMobileApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}