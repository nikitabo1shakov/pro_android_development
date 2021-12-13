package com.nikitabolshakov.proandroiddevelopment.domain.interactor

import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.data.model.SkyengDataModel
import com.nikitabolshakov.proandroiddevelopment.data.repository.remote.RepositoryRemote
import com.nikitabolshakov.proandroiddevelopment.data.repository.local.RepositoryLocal

class MainInteractor(
    private val repositoryRemote: RepositoryRemote<List<SkyengDataModel>>,
    private val repositoryLocal: RepositoryLocal<List<SkyengDataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}