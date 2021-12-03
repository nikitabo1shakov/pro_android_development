package com.nikitabolshakov.proandroiddevelopment.domain.interactor

import com.nikitabolshakov.proandroiddevelopment.di.NAME_LOCAL
import com.nikitabolshakov.proandroiddevelopment.di.NAME_REMOTE
import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import com.nikitabolshakov.proandroiddevelopment.data.repository.Repository
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