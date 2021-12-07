package com.nikitabolshakov.proandroiddevelopment.di

import com.nikitabolshakov.proandroiddevelopment.data.dataSource.RetrofitImplementation
import com.nikitabolshakov.proandroiddevelopment.data.dataSource.RoomDataBaseImplementation
import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import com.nikitabolshakov.proandroiddevelopment.data.repository.Repository
import com.nikitabolshakov.proandroiddevelopment.data.repository.RepositoryImplementation
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.MainInteractor
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModel.MainActivityViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}
val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainActivityViewModel(get()) }
}