package com.nikitabolshakov.data.repository.local

import com.nikitabolshakov.data.dataSource.local.SearchResultDtoLocal
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.model.SearchResultDto

class RepositoryLocalImpl(
    private val searchResultDto: SearchResultDtoLocal<List<SearchResultDto>>
) : RepositoryLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> =
        searchResultDto.getData(word)

    override suspend fun saveToDB(appState: AppState) {
        searchResultDto.saveToDB(appState)
    }
}