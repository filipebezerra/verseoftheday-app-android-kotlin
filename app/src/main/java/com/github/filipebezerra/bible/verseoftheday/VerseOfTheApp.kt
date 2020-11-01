package com.github.filipebezerra.bible.verseoftheday

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class VerseOfTheApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(object : DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })
    }
}