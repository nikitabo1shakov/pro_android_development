package com.nikitabolshakov.proandroiddevelopment.application

import android.app.Application
import com.nikitabolshakov.proandroiddevelopment.di.application
import com.nikitabolshakov.proandroiddevelopment.di.mainScreen
import org.koin.core.context.GlobalContext.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}