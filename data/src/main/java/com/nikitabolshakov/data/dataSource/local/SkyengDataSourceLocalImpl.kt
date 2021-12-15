package com.nikitabolshakov.data.dataSource.local

import com.nikitabolshakov.data.convertDataModelSuccessToEntity
import com.nikitabolshakov.data.mapHistoryEntityToSearchResult
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.model.SkyengDataModel
import com.nikitabolshakov.data.room.HistoryDao

class SkyengDataSourceLocalImpl(
    private val historyDao: HistoryDao
) : SkyengDataSourceLocal<List<SkyengDataModel>> {

    override suspend fun getData(word: String): List<SkyengDataModel> =
        mapHistoryEntityToSearchResult(historyDao.all())

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}