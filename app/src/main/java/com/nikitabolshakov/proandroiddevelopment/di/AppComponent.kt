package com.nikitabolshakov.proandroiddevelopment.di

import android.app.Application
import com.nikitabolshakov.proandroiddevelopment.application.TranslatorApp
import com.nikitabolshakov.proandroiddevelopment.di.modules.ActivityModule
import com.nikitabolshakov.proandroiddevelopment.di.modules.InteractorModule
import com.nikitabolshakov.proandroiddevelopment.di.modules.RepositoryModule
import com.nikitabolshakov.proandroiddevelopment.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(englishVocabularyApp: TranslatorApp)
}