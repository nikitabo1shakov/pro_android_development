package com.nikitabolshakov.proandroiddevelopment.data.dataSource

import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import com.nikitabolshakov.proandroiddevelopment.data.room.HistoryDao
import com.nikitabolshakov.proandroiddevelopment.utils.convertDataModelSuccessToEntity
import com.nikitabolshakov.proandroiddevelopment.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(
    private val historyDao: HistoryDao
) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> =
        mapHistoryEntityToSearchResult(historyDao.all())

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}