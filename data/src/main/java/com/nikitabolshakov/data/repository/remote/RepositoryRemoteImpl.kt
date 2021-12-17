package com.nikitabolshakov.data.repository.remote

import com.nikitabolshakov.data.dataSource.remote.SearchResultDtoRemote
import com.nikitabolshakov.model.SearchResultDto

class RepositoryRemoteImpl(
    private val searchResultDto: SearchResultDtoRemote<List<SearchResultDto>>
) : RepositoryRemote<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> =
        searchResultDto.getData(word)
}