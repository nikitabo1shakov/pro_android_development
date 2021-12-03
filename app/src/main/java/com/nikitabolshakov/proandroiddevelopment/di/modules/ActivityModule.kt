package com.nikitabolshakov.proandroiddevelopment.di.modules

import com.nikitabolshakov.proandroiddevelopment.presentation.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}