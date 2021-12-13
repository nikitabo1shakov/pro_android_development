package com.nikitabolshakov.proandroiddevelopment.di

import androidx.room.Room
import com.nikitabolshakov.proandroiddevelopment.data.dataSource.local.SkyengDataSourceLocalImpl
import com.nikitabolshakov.proandroiddevelopment.data.dataSource.remote.SkyengDataSourceRemoteImpl
import com.nikitabolshakov.proandroiddevelopment.data.model.SkyengDataModel
import com.nikitabolshakov.proandroiddevelopment.data.repository.remote.RepositoryRemote
import com.nikitabolshakov.proandroiddevelopment.data.repository.remote.RepositoryRemoteImpl
import com.nikitabolshakov.proandroiddevelopment.data.repository.local.RepositoryLocalImpl
import com.nikitabolshakov.proandroiddevelopment.data.repository.local.RepositoryLocal
import com.nikitabolshakov.proandroiddevelopment.data.room.HistoryDataBase
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.HistoryInteractor
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.MainInteractor
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModels.HistoryActivityViewModel
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModels.MainActivityViewModel
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<RepositoryRemote<List<SkyengDataModel>>> {
        RepositoryRemoteImpl(
            SkyengDataSourceRemoteImpl()
        )
    }
    single<RepositoryLocal<List<SkyengDataModel>>> {
        RepositoryLocalImpl(
            SkyengDataSourceLocalImpl(historyDao = get())
        )
    }
}

val mainScreen = module {
    factory { MainActivityViewModel(mainInteractor = get()) }
    factory { MainInteractor(repositoryRemote = get(), repositoryLocal = get()) }
}

val historyScreen = module {
    factory { HistoryActivityViewModel(historyInteractor = get()) }
    factory { HistoryInteractor(repositoryRemote = get(), repositoryLocal = get()) }
}