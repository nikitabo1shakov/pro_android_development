package com.nikitabolshakov.historyscreen.domain.interactor

import com.nikitabolshakov.core.domain.interactor.Interactor
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.model.SkyengDataModel
import com.nikitabolshakov.data.repository.local.RepositoryLocal
import com.nikitabolshakov.data.repository.remote.RepositoryRemote

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