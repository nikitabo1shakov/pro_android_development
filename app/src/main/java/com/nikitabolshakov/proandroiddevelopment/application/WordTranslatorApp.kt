package com.nikitabolshakov.proandroiddevelopment.application

import android.app.Application
import com.nikitabolshakov.proandroiddevelopment.di.application
import com.nikitabolshakov.proandroiddevelopment.di.historyScreen
import com.nikitabolshakov.proandroiddevelopment.di.mainScreen
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