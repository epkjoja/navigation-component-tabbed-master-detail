package com.myapplication.activity

import android.app.Application
import androidx.annotation.Keep
import com.myapplication.BuildConfig
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