package com.nikitabolshakov.data.repository.remote

import com.nikitabolshakov.data.dataSource.remote.SkyengDataSourceRemote
import com.nikitabolshakov.model.SkyengDataModel

class RepositoryRemoteImpl(
    private val skyengDataSource: SkyengDataSourceRemote<List<SkyengDataModel>>
) : RepositoryRemote<List<SkyengDataModel>> {

    override suspend fun getData(word: String): List<SkyengDataModel> =
        skyengDataSource.getData(word)
}