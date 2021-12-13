package com.nikitabolshakov.proandroiddevelopment.domain.interactor

import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.data.model.SkyengDataModel
import com.nikitabolshakov.proandroiddevelopment.data.repository.remote.RepositoryRemote
import com.nikitabolshakov.proandroiddevelopment.data.repository.local.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: RepositoryRemote<List<SkyengDataModel>>,
    private val repositoryLocal: RepositoryLocal<List<SkyengDataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}