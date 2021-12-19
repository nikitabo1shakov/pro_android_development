package com.nikitabolshakov.data.dataSource.local

import com.nikitabolshakov.data.convertDataModelSuccessToEntity
import com.nikitabolshakov.data.mapHistoryEntityToSearchResult
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.data.room.HistoryDao
import com.nikitabolshakov.model.SearchResultDto

class SearchResultDtoLocalImpl(
    private val historyDao: HistoryDao
) : SearchResultDtoLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> =
        mapHistoryEntityToSearchResult(historyDao.all())

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}