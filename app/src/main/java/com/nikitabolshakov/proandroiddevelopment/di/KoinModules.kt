package com.nikitabolshakov.proandroiddevelopment.di

import androidx.room.Room
import com.nikitabolshakov.data.dataSource.local.SkyengDataSourceLocalImpl
import com.nikitabolshakov.data.dataSource.remote.SkyengDataSourceRemoteImpl
import com.nikitabolshakov.model.SkyengDataModel
import com.nikitabolshakov.data.repository.remote.RepositoryRemote
import com.nikitabolshakov.data.repository.remote.RepositoryRemoteImpl
import com.nikitabolshakov.data.repository.local.RepositoryLocalImpl
import com.nikitabolshakov.data.repository.local.RepositoryLocal
import com.nikitabolshakov.data.room.HistoryDataBase
import com.nikitabolshakov.historyscreen.domain.interactor.HistoryInteractor
import com.nikitabolshakov.historyscreen.presentation.view.activity.HistoryActivity
import com.nikitabolshakov.historyscreen.presentation.viewModel.HistoryActivityViewModel
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.MainInteractor
import com.nikitabolshakov.proandroiddevelopment.presentation.view.activity.main.MainActivity
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {

    single {
        Room.databaseBuilder(
            get(), HistoryDataBase::class.java, "HistoryDB"
        ).build()
    }

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
    scope<MainActivity> {
        scoped { MainInteractor(repositoryRemote = get(), repositoryLocal = get()) }
        viewModel { MainActivityViewModel(mainInteractor = get()) }
    }
}

val historyScreen = module {
    scope<HistoryActivity> {
        scoped { HistoryInteractor(repositoryRemote = get(), repositoryLocal = get()) }
        viewModel { HistoryActivityViewModel(historyInteractor = get()) }
    }
}