package com.nikitabolshakov.proandroiddevelopment.data.repository

import com.nikitabolshakov.proandroiddevelopment.data.dataSource.DataSourceLocal
import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel

class RepositoryImplementationLocal(
    private val dataSource: DataSourceLocal<List<DataModel>>
) : RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = dataSource.getData(word)

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}