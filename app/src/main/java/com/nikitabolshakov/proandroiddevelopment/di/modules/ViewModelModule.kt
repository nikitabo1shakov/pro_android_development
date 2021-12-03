package com.nikitabolshakov.proandroiddevelopment.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitabolshakov.proandroiddevelopment.di.ViewModelFactory
import com.nikitabolshakov.proandroiddevelopment.di.ViewModelKey
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModel.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    protected abstract fun mainViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}