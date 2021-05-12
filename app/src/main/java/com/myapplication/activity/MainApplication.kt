package com.myapplication.activity

import androidx.annotation.Keep
import androidx.multidex.MultiDexApplication
import com.myapplication.BuildConfig
import timber.log.Timber

@Keep
class MainApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}