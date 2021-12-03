package com.nikitabolshakov.proandroiddevelopment.di.modules

import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import com.nikitabolshakov.proandroiddevelopment.data.dataSource.DataSource
import com.nikitabolshakov.proandroiddevelopment.data.dataSource.RetrofitImplementation
import com.nikitabolshakov.proandroiddevelopment.data.dataSource.RoomDataBaseImplementation
import com.nikitabolshakov.proandroiddevelopment.data.repository.Repository
import com.nikitabolshakov.proandroiddevelopment.data.repository.RepositoryImplementation
import com.nikitabolshakov.proandroiddevelopment.di.NAME_LOCAL
import com.nikitabolshakov.proandroiddevelopment.di.NAME_REMOTE
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> =
        RoomDataBaseImplementation()
}