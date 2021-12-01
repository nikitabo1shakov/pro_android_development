package com.nikitabolshakov.proandroiddevelopment.di

import com.nikitabolshakov.proandroiddevelopment.model.data.DataModel
import com.nikitabolshakov.proandroiddevelopment.model.repository.Repository
import com.nikitabolshakov.proandroiddevelopment.view.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}