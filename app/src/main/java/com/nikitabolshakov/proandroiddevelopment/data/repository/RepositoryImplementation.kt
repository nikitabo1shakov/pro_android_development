package com.nikitabolshakov.proandroiddevelopment.data.repository

import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import com.nikitabolshakov.proandroiddevelopment.data.dataSource.DataSource

class RepositoryImplementation(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}