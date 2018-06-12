package com.brian.mvvm_kotlin

import android.app.Application
import timber.log.Timber

/**
 * @author Brian
 * @date: 6/12/18
 */


class ApplicationKotlin : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}