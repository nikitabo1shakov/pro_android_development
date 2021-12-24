package com.nikitabolshakov.wordTranslator.application

import android.app.Application
import com.nikitabolshakov.wordTranslator.di.application
import com.nikitabolshakov.wordTranslator.di.historyScreen
import com.nikitabolshakov.wordTranslator.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordTranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    application, mainScreen, historyScreen
                )
            )
        }
    }
}