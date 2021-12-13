package com.nikitabolshakov.proandroiddevelopment.di

import androidx.room.Room
import com.nikitabolshakov.proandroiddevelopment.data.dataSource.RetrofitImplementation
import com.nikitabolshakov.proandroiddevelopment.data.dataSource.RoomDataBaseImplementation
import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import com.nikitabolshakov.proandroiddevelopment.data.repository.Repository
import com.nikitabolshakov.proandroiddevelopment.data.repository.RepositoryImplementation
import com.nikitabolshakov.proandroiddevelopment.data.repository.RepositoryImplementationLocal
import com.nikitabolshakov.proandroiddevelopment.data.repository.RepositoryLocal
import com.nikitabolshakov.proandroiddevelopment.data.room.HistoryDataBase
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.HistoryInteractor
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.MainInteractor
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModel.HistoryViewModel
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModel.MainActivityViewModel
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainActivityViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}