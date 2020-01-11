package com.myapplication

import android.app.Application
import androidx.annotation.Keep
import timber.log.Timber

@Keep
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}