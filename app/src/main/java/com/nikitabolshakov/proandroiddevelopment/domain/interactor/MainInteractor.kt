package com.nikitabolshakov.proandroiddevelopment.domain.interactor

import com.nikitabolshakov.core.domain.interactor.Interactor
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.data.repository.remote.RepositoryRemote
import com.nikitabolshakov.data.repository.local.RepositoryLocal
import com.nikitabolshakov.model.SearchResultDto
import com.nikitabolshakov.proandroiddevelopment.utils.mapSearchResultToResult

class MainInteractor(
    private val repositoryRemote: RepositoryRemote<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }
}