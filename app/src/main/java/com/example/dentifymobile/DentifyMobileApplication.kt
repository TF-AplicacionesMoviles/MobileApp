package com.example.dentifymobile

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class DentifyMobileApplication: Application() {
    companion object {
        lateinit var instance: DentifyMobileApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        instance = this
    }
}