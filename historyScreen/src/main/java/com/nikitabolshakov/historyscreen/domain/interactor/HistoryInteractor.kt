package com.nikitabolshakov.historyscreen.domain.interactor

import com.nikitabolshakov.core.domain.interactor.Interactor
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.data.repository.local.RepositoryLocal
import com.nikitabolshakov.data.repository.remote.RepositoryRemote
import com.nikitabolshakov.model.SearchResultDto
import com.nikitabolshakov.model.mapSearchResultToResult

class HistoryInteractor(
    private val repositoryRemote: RepositoryRemote<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}