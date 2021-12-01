package com.nikitabolshakov.proandroiddevelopment.di.modules

import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import com.nikitabolshakov.proandroiddevelopment.data.repository.Repository
import com.nikitabolshakov.proandroiddevelopment.di.NAME_LOCAL
import com.nikitabolshakov.proandroiddevelopment.di.NAME_REMOTE
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.MainInteractor
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