package com.nikitabolshakov.proandroiddevelopment.di

import com.nikitabolshakov.proandroiddevelopment.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}