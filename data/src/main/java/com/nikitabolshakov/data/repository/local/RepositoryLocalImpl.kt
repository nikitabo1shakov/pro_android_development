package com.nikitabolshakov.data.repository.local

import com.nikitabolshakov.data.dataSource.local.SkyengDataSourceLocal
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.model.SkyengDataModel

class RepositoryLocalImpl(
    private val skyengDataSource: SkyengDataSourceLocal<List<SkyengDataModel>>
) : RepositoryLocal<List<SkyengDataModel>> {

    override suspend fun getData(word: String): List<SkyengDataModel> =
        skyengDataSource.getData(word)

    override suspend fun saveToDB(appState: AppState) {
        skyengDataSource.saveToDB(appState)
    }
}