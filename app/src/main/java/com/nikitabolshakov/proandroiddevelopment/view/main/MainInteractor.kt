package com.nikitabolshakov.proandroiddevelopment.view.main

import com.nikitabolshakov.proandroiddevelopment.di.NAME_LOCAL
import com.nikitabolshakov.proandroiddevelopment.di.NAME_REMOTE
import com.nikitabolshakov.proandroiddevelopment.model.data.AppState
import com.nikitabolshakov.proandroiddevelopment.model.data.DataModel
import com.nikitabolshakov.proandroiddevelopment.model.repository.Repository
import com.nikitabolshakov.proandroiddevelopment.interactor.Interactor
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
    }
}