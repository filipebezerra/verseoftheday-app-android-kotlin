package com.github.filipebezerra.bible.verseoftheday

import android.app.Application
import timber.log.Timber

class VotdApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BuildConfig.DEBUG.takeIf { it }?.let { Timber.plant(Timber.DebugTree()) }
    }
}