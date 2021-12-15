package com.nikitabolshakov.proandroiddevelopment.data.dataSource.local

import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.data.model.SkyengDataModel
import com.nikitabolshakov.proandroiddevelopment.data.room.HistoryDao
import com.nikitabolshakov.proandroiddevelopment.utils.convertDataModelSuccessToEntity
import com.nikitabolshakov.proandroiddevelopment.utils.mapHistoryEntityToSearchResult

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